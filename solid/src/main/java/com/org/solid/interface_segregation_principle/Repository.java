package com.org.solid.interface_segregation_principle;


import java.awt.print.Pageable;

/**
 * ISP – Interface segregation principle example
 *
 * @author PhungHuynh
 */
interface Repository<T, ID> {

    Iterable<T> findAll();

    T findOne(ID id);

    T save(T entity);

    void update(T entity);

    void delete(ID id);

//    Page<T> findAll(Pageable pageable);

//    Iterable<T> findAll(Sort sort);
}

interface CrudRepository<T, ID> {

    Iterable<T> findAll();

    T findOne(ID id);

    T save(T entity);

    void update(T entity);

    void delete(ID id);

}

interface PagingAndSortingRepository<T, ID> extends CrudRepository<T, ID> {

//    Page<T> findAll(Pageable pageable);

//    Iterable<T> findAll(Sort sort);
}