package com.ferdielik.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ferdielik.entity.Btc;

@Repository
@Transactional
public class BtcDAO extends AbstractDAO<Btc>
{
}
