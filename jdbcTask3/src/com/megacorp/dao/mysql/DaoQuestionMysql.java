package com.megacorp.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.megacorp.dao.ConnectionPool;
import com.megacorp.dao.DaoQuestion;
import com.megacorp.dao.exception.DaoException;
import com.megacorp.domain.QuestionEntity;

public class DaoQuestionMysql implements DaoQuestion {

	@Override
	public List<QuestionEntity> getQuestions() {
		QuestionEntity question = null;
		List<QuestionEntity> questionList = new ArrayList<>();
		Connection cn = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		try {
			cn = ConnectionPool.getInstance().getConnection();
			statement = cn
					.prepareStatement("SELECT id, question  FROM questions ");
			set = statement.executeQuery();

			while (set.next()) {
				int id = set.getInt("id");
				String questionName = set.getString("question");
				question = new QuestionEntity(id, questionName);
				questionList.add(question);
			}

			return questionList;

		} catch (SQLException e) {
			throw new DaoException("Some errors occurred during DB access!", e);
		} finally {
			ConnectionPool.closeResourses(cn, statement, set);
		}

	}

	@Override
	public void insertQuestion(QuestionEntity question) {
		Connection cn = null;
		PreparedStatement statement = null;
		
		try {
			cn = ConnectionPool.getInstance().getConnection();
			statement = cn
					.prepareStatement("INSERT INTO questions(question) VALUES(?); ");
			statement.setString(1, question.getQuestion());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException("Some errors occurred during DB access!", e);
		} finally {
			ConnectionPool.closeResourses(cn, statement);
		}

	}

	@Override
	public QuestionEntity getQuestion(QuestionEntity question) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateQuestion(QuestionEntity question) {
		Connection cn = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		String sql = "UPDATE `questions` SET `question`=? WHERE `id`=?";
		try {
			cn = ConnectionPool.getInstance().getConnection();
			statement = cn.prepareStatement(sql);
			statement.setString(1, question.getQuestion());
			statement.setInt(2, question.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException("Some errors occurred during DB access!", e);
		} finally {
			ConnectionPool.closeResourses(cn, statement, set);
		}

	}

	@Override
	public void deleteQuestion(QuestionEntity question) {
		Connection cn = null;
		PreparedStatement statement = null;
		
		try {
			cn = ConnectionPool.getInstance().getConnection();
			statement = cn
					.prepareStatement("DELETE FROM questions WHERE id =?; ");
			statement.setInt(1, question.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException("Some errors occurred during DB access!", e);
		} finally {
			ConnectionPool.closeResourses(cn, statement);
		}


	}

	@Override
	public int countRows() {
		Connection cn = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		try {
			cn = ConnectionPool.getInstance().getConnection();
			statement = cn
					.prepareStatement("SELECT count(*) AS cnt  FROM questions ");
			set = statement.executeQuery();

			if (set.next()) {
				int count = set.getInt("cnt");
				return count;
			}

			return 0;

		} catch (SQLException e) {
			throw new DaoException("Some errors occurred during DB access!", e);
		} finally {
			ConnectionPool.closeResourses(cn, statement, set);
		}

	}

}
