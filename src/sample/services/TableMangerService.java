package sample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.mapper.ConfigMapper;
import sample.mapper.DiabloPublishMapper;
import sample.mapper.TableCreatorMapper;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/10/23 11:50
 */

@Service
public class TableMangerService {
    @Autowired
    public ConfigMapper configMapper;

    @Autowired
    public DiabloPublishMapper diabloPublishMapper;

    @Autowired
    public TableCreatorMapper tableCreatorMapper;

    public TableMangerService() {

    }


    public void init() {
        tableCreatorMapper.createTables();
    }

}
