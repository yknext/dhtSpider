package next.dht.service;

import next.dht.entity.MagnetEntity;
import next.dht.entity.MagnetModel;
import next.dht.repositiory.MagnetRepository;
import next.dht.util.Constants;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * Created by next on 2016/8/10.
 */
@Component
public class MagnetService extends Thread{

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    ObjectMapper objectMapper;

    @Autowired
    MagnetRepository magnetRepository;

    public MagnetService()
    {
        objectMapper = new ObjectMapper();
    }

    @Override
    public void run() {
            while(true)
            {
                try {
                    if(stringRedisTemplate.opsForList().size(Constants.METADATA) > 0) {
                        String json = stringRedisTemplate.opsForList().rightPop(Constants.METADATA);
                        MagnetModel magnetModel = objectMapper.readValue(json, MagnetModel.class);
                        MagnetEntity oldMagnet = magnetRepository.findMagnetByMagnet(magnetModel.getMagnet());
                        if (null == oldMagnet) {
                            MagnetEntity magnetEntity = new MagnetEntity();
                            magnetEntity.setMagnet(magnetModel.getMagnet());
                            magnetEntity.setName(magnetModel.getName());
                            magnetEntity.setmCount(1);
                            magnetEntity.setUpdateTime(new Date(Long.valueOf(magnetModel.getFetchedAt())));
                            magnetRepository.save(magnetEntity);
                        } else {
                            oldMagnet.setmCount(oldMagnet.getmCount() + 1);
                            oldMagnet.setUpdateTime(new Date(Long.valueOf(magnetModel.getFetchedAt())));
                            magnetRepository.save(oldMagnet);
                        }
                    }
                    else
                    {
                        Thread.sleep(10000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }
}
