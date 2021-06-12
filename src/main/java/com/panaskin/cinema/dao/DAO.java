package com.panaskin.cinema.dao;

import java.util.List;

import com.panaskin.cinema.exception.DAOException;

public interface DAO<T> {
	boolean save(T t) throws DAOException ;
	T find(long id) throws DAOException;
	void update(T t) throws DAOException;
	int delete(long id) throws DAOException;
	List<T> findAll() throws DAOException;
}