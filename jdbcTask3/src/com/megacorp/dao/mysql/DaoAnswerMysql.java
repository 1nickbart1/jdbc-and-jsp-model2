package com.megacorp.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.megacorp.dao.ConnectionPool;
import com.megacorp.dao.DaoAnswer;
import com.megacorp.dao.exception.DaoException;
import com.megacorp.domain.AnswerEntity;
import com.megacorp.domain.QuestionEntity;

public class DaoAnswerMysql implements DaoAnswer {

	@Override
	public List<AnswerEntity> getAnswers() {
		AnswerEntity answer = null;
		List<AnswerEntity> answerList = new ArrayList<>();
		Connection cn = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		try {
			cn = ConnectionPool.getInstance().getConnection();
			statement = cn
					.prepareStatement("SELECT id, answer,question_id,is_correct  FROM answers ");
			set = statement.executeQuery();

			while (set.next()) {
				int id = set.getInt("id");
				String answerName = set.getString("answer");
				int questionId = set.getInt("question_id");
				boolean isCorrect = set.getBoolean("is_correct");
				answer = new AnswerEntity(id, answerName, questionId, isCorrect);
				answerList.add(answer);
			}

			return answerList;

		} catch (SQLException e) {
			throw new DaoException("Some errors occurred during DB access!", e);
		} finally {
			ConnectionPool.closeResourses(cn, statement, set);
		}

	}

	@Override
	public List<AnswerEntity> getAnswersForQuestion(QuestionEntity quest) {
		AnswerEntity answer = null;
		List<AnswerEntity> answerList = new ArrayList<>();
		Connection cn = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		try {
			cn = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT id, answer,question_id,is_correct  FROM answers WHERE question_id =? ;";
			statement = cn.prepareStatement(sql);
			statement.setInt(1, quest.getId());
			set = statement.executeQuery();

			while (set.next()) {
				int id = set.getInt("id");
				String answerName = set.getString("answer");
				int questionId = set.getInt("question_id");
				boolean isCorrect = set.getBoolean("is_correct");
				answer = new AnswerEntity(id, answerName, questionId, isCorrect);
				answerList.add(answer);
			}

			return answerList;

		} catch (SQLException e) {
			throw new RuntimeException(
					"Some errors occurred during DB access!", e);
		} finally {
			ConnectionPool.closeResourses(cn, statement, set);
		}

	}

	@Override
	public void insertAnswer(AnswerEntity answer) {
		Connection cn = null;
		PreparedStatement statement = null;	

		try {
			cn = ConnectionPool.getInstance().getConnection();
			String sql ="INSERT INTO answers(answer,question_id,is_correct) VALUES(?,?,?); ";
			statement = cn.prepareStatement(sql);
			statement.setString(1, answer.getAnswer());
			statement.setInt(2, answer.getQuestionId());
			statement.setBoolean(3, answer.isCorrect());	
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException("Some errors occurred during DB access!", e);
		} finally {
			ConnectionPool.closeResourses(cn, statement);
		}
	}

	@Override
	public void updateAnswer(AnswerEntity answer) {
		Connection cn = null;
		PreparedStatement statement = null;	

		try {
			cn = ConnectionPool.getInstance().getConnection();
			String sql ="UPDATE answers SET answer = ?,is_correct = ?  WHERE id =? ; ";
			statement = cn.prepareStatement(sql);
			statement.setString(1, answer.getAnswer());
			statement.setBoolean(2, answer.isCorrect());			
			statement.setInt(3, answer.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException("Some errors occurred during DB access!", e);
		} finally {
			ConnectionPool.closeResourses(cn, statement);
		}

	}

	@Override
	public void deleteAnswer(AnswerEntity answer) {
		Connection cn = null;
		PreparedStatement statement = null;	

		try {
			cn = ConnectionPool.getInstance().getConnection();
			String sql ="DELETE FROM answers WHERE id = ?; ";
			statement = cn.prepareStatement(sql);			
			statement.setInt(1, answer.getId());		
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException("Some errors occurred during DB access!", e);
		} finally {
			ConnectionPool.closeResourses(cn, statement);
		}

	}

	@Override
	public AnswerEntity getAnswer(QuestionEntity quest) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int countRows() {
		Connection cn = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		try {
			cn = ConnectionPool.getInstance().getConnection();
			statement = cn
					.prepareStatement("SELECT count(*) AS cnt  FROM answers ");
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

	@Override
	public List<AnswerEntity> getCorrectAnswers(QuestionEntity quest) {
		AnswerEntity answer = null;
		List<AnswerEntity> answerList = new ArrayList<>();
		Connection cn = null;
		PreparedStatement statement = null;
		ResultSet set = null;

		try {
			cn = ConnectionPool.getInstance().getConnection();
			String sql = "SELECT id, answer,question_id,is_correct  FROM answers WHERE question_id =? AND is_correct = true ;";
			statement = cn.prepareStatement(sql);
			statement.setInt(1, quest.getId());
			set = statement.executeQuery();

			while (set.next()) {
				int id = set.getInt("id");
				String answerName = set.getString("answer");
				int questionId = set.getInt("question_id");
				boolean isCorrect = set.getBoolean("is_correct");
				answer = new AnswerEntity(id, answerName, questionId, isCorrect);
				answerList.add(answer);
			}

			return answerList;

		} catch (SQLException e) {
			throw new RuntimeException(
					"Some errors occurred during DB access!", e);
		} finally {
			ConnectionPool.closeResourses(cn, statement, set);
		}
	}

}
