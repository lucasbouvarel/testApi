/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testapis;

import com.lightstreamer.ls_client.HandyTableListener;
import com.lightstreamer.ls_client.SubscribedTableKey;
import com.lightstreamer.ls_client.UpdateInfo;

public class HandyTableListenerAdapter implements HandyTableListener {

	private SubscribedTableKey subscribedTableKey;

	@Override
	public void onUpdate(int i, String s, UpdateInfo updateInfo) {
	}

	@Override
	public void onSnapshotEnd(int i, String s) {
	}

	@Override
	public void onRawUpdatesLost(int i, String s, int i2) {
	}

	@Override
	public void onUnsubscr(int i, String s) {
	}

	@Override
	public void onUnsubscrAll() {
	}

	public SubscribedTableKey getSubscribedTableKey() {
		return subscribedTableKey;
	}

	public void setSubscribedTableKey(SubscribedTableKey subscribedTableKey) {
		this.subscribedTableKey = subscribedTableKey;
	}
}
