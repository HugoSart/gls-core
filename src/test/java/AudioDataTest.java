import com.hugovs.gls.core.AudioData;
import org.junit.Assert;
import org.junit.Test;

public class AudioDataTest {

    @Test
    public void wrapAndUnwrap() {
        byte[] bytes = new byte[] {0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9};

        AudioData wrap = AudioData.wrap(bytes);
        byte[] unwrap = AudioData.unwrap(wrap);

        Assert.assertArrayEquals(bytes, unwrap);
    }

}
