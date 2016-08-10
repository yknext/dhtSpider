package next.dht.repositiory;

import next.dht.entity.MagnetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

/**
 * Created by next on 2016/8/10.
 */
public interface MagnetRepository extends JpaRepository<MagnetEntity,Long> {
    public MagnetEntity findMagnetByMagnet(String magnet);
}
