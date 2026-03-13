package com.cloud.store.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.store.entity.Store;
import com.cloud.store.mapper.StoreMapper;
import com.cloud.store.service.StoreService;
import org.springframework.stereotype.Service;

@Service
//@RefreshScope
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {

}
