package com.his.ar.dao.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.his.ar.dao.ARUserMasterDao;
import com.his.ar.entity.ARUserMaster;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ArUserMasterDaoTest {

	@Autowired
	private ARUserMasterDao arUserMasterDao;

	@Test
	public void findByIdTest1() {
		Optional<ARUserMaster> optEntity = arUserMasterDao.findById(43);
		ARUserMaster entity = optEntity.get();
		assertNotNull(entity);
	}

	@Test
	public void findByIdTest2() {
		Optional<ARUserMaster> optEntity = arUserMasterDao.findById(44);
		ARUserMaster entity = optEntity.get();
		assertNull(entity);
	}

}
