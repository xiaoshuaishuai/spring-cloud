import org.junit.Test;
import org.junit.runner.RunWith;
import org.my.spring.Application;
import org.my.spring.receiver.Receiver;
import org.my.spring.sender.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class MsgTest {
	@Autowired
	Sender sender;

//	@Autowired
//	Receiver receiver;

	@Test
	public void send() {
		sender.send();
	}
}
