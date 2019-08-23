/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testapis;

/**
 *
 * @author lucasbouvarel
 */



import com.lightstreamer.ls_client.ConnectionListener;
import com.lightstreamer.ls_client.PushConnException;
import com.lightstreamer.ls_client.PushServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionListenerAdapter implements ConnectionListener {

	private static final Logger LOG = LoggerFactory
			.getLogger(ConnectionListenerAdapter.class);

	@Override
	public void onConnectionEstablished() {
            System.out.println("Connection ");
		LOG.debug("onConnectionEstablished");
	}

	@Override
	public void onSessionStarted(boolean b) {
            System.out.println("Connexion started");
		LOG.debug("onSessionStarted " + b);
	}

	@Override
	public void onNewBytes(long l) {
            System.out.println("newBytes");
		LOG.debug("onNewBytes " + l);
	}

	@Override
	public void onDataError(PushServerException e) {
            System.out.println("Data error");
		LOG.debug("onDataError ", e);
	}

	@Override
	public void onActivityWarning(boolean b) {
            System.out.println("Activity Warning");
		LOG.debug("onActivityWarning");
	}

	@Override
	public void onClose() {
            System.out.println("On close");
		LOG.debug("onClose");
	}

	@Override
	public void onEnd(int i) {
            System.out.println("On end");
		LOG.debug("onEnd " + i);
	}

	@Override
	public void onFailure(PushServerException e) {
            System.out.println("failure");
		LOG.debug("onFailure", e);
		throw new RuntimeException("onFailure " + e);
	}

	@Override
	public void onFailure(PushConnException e) {
            System.out.println("failure");
		LOG.debug("onFailure", e);
		throw new RuntimeException("onFailure " + e);
	}

}
