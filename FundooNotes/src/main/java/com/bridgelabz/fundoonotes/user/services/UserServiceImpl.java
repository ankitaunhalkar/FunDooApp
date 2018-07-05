package com.bridgelabz.fundoonotes.user.services;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.user.dao.ITokenDao;
import com.bridgelabz.fundoonotes.user.dao.IUserDao;
import com.bridgelabz.fundoonotes.user.model.Mail;
import com.bridgelabz.fundoonotes.user.model.LoginDto;
import com.bridgelabz.fundoonotes.user.model.RegisterDto;
import com.bridgelabz.fundoonotes.user.model.ResetPasswordDto;
import com.bridgelabz.fundoonotes.user.model.User;
import com.bridgelabz.fundoonotes.user.util.MailProducer;
import com.bridgelabz.fundoonotes.user.util.TokenUtil;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserDao userDao;

	@Autowired
	User user;

	@Autowired
	MailProducer mailProducer;

	@Autowired
	Mail mail;

	@Autowired
	ITokenDao tokenDao;

	@Autowired
	JavaMailSender mailSender;

	@Override
	@Transactional
	public long register(RegisterDto registrationUser, String url) {

		long registeredId = 0;

		// checking if mail id is already exists or not
		User registerDaoStatus = userDao.getByEmail(registrationUser.getEmail());

		if (registerDaoStatus != null) {

			return registeredId;

		}

		// setting into user model
		user.setName(registrationUser.getName());
		user.setEmail(registrationUser.getEmail());
		user.setPassword(registrationUser.getPassword());
		user.setPhone(registrationUser.getPhone());
		
		// password encrypting
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

		// if not existing then save
		registeredId = userDao.save(user);

		// token generating
		String token = TokenUtil.createJWT(String.valueOf(registeredId), registrationUser.getName(), "Verification", 24 * 3600 * 1000);

		// setting to email model
		mail.setName(registrationUser.getName());
		mail.setTo(registrationUser.getEmail());
		mail.setSubject("Fundoo Verfication");
		mail.setBody(url + token);
		
		// mail sending
		mailProducer.sendMail(mail);

		// setting into redis cache
		tokenDao.setToken(String.valueOf(registeredId), token);

		return registeredId;
	}

	@Override
	@Transactional
	public String login(LoginDto loginUser) {

		String token = null;
		
		// checking if mail id exists or not
		User loggedUser = userDao.getByEmail(loginUser.getEmail());

		// if exists then matching the password
		if ((loggedUser != null) && (loggedUser.isVerified() == true)
				&& (BCrypt.checkpw(loginUser.getPassword(), loggedUser.getPassword()))) {

			token = TokenUtil.createJWT(String.valueOf(loggedUser.getId()), loggedUser.getName(), "Login", 24 * 3600 * 1000);
			
		}

		return token;
	}

	@Override
	@Transactional
	public boolean verify(String token) {

		boolean status = false;
		
		// Decoding token and validating
		long id = TokenUtil.parseJWT(token);

		//getting token from redis
		String redisToken = tokenDao.getToken(String.valueOf(id));

		if (token.equals(redisToken)) {
			
			// Fetching user by id
			User user = userDao.getById(id);

			// Setting isVerified value to true
			user.setVerified(true);

			// Updating isVerified value in db
			userDao.update(user);

			//Deleting token from redis
			tokenDao.deleteToken(String.valueOf(id));

			return status = true;
		}
		
		return status;
	}

	@Override
	@Transactional
	public void forgotPassword(ResetPasswordDto userDto, String url) {

		// Fetching user by emailId
		User user = userDao.getByEmail(userDto.getEmailId());

		// generating new token
		String token = TokenUtil.createJWT(String.valueOf(user.getId()), "Ankita", "Verification", 24 * 3600 * 1000);

		// setting to email model
		mail.setName(user.getName());
		mail.setTo(user.getEmail());
		mail.setSubject("Reset Password Verification");
		mail.setBody(url + token);

		// mail sending
		mailProducer.sendMail(mail);

		tokenDao.setToken(String.valueOf(user.getId()), token);

	}

	@Override
	@Transactional
	public boolean resetPassword(ResetPasswordDto userDto, String token) {

		boolean status = false;

		// decoding token and validating
		long id = TokenUtil.parseJWT(token);

		String redisToken = tokenDao.getToken(String.valueOf(id));

		// getting user by id
		User user = userDao.getById(id);

		// checking if it is a verified account or not
		// and matching token and than reseting password
		if (token.equals(redisToken) && user.isVerified()) {

			// encrypting new password and setting it
			user.setPassword(BCrypt.hashpw(userDto.getNewPassword(), BCrypt.gensalt()));

			// Updating new password in db
			status = userDao.update(user);

			tokenDao.deleteToken(String.valueOf(id));

		}
		return status;
	}

	@Override
	@Transactional
	public void mailSender(final Mail mailObj) {
		
		final MimeMessagePreparator message = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {

				mimeMessage.setRecipients(Message.RecipientType.TO, mailObj.getTo());
				mimeMessage.setSubject(mailObj.getSubject());
				mimeMessage.setText("Dear " + mailObj.getName() + ",\n" + mailObj.getBody());

			}
		};

		mailSender.send(message);
	}

}
