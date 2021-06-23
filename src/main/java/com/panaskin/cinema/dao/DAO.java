package com.panaskin.cinema.dao;

import java.util.List;

import com.panaskin.cinema.exception.DAOException;

public interface DAO<T> {
	long save(T t) throws DAOException ;
	T find(long id) throws DAOException;
	T update(T t) throws DAOException;
	void delete(long id) throws DAOException;
	List<T> findAll() throws DAOException;
}