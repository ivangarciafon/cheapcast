package at.maui.cheapcast.chromecast;

import java.util.ArrayList;
import java.util.LinkedList;

public class App {

    private String mName, mState, mLink, mConnectionSvcURL, mReceiverUrl;
    private ArrayList<String> mProtocols;

    private ConnectionSocket mControlChannel;
    private ArrayList<SessionSocket> mRemotes;
    private ArrayList<ReceiverSocket> mReceivers;
    private LinkedList<String> mMessageBuffer;

    public App(String name, String receiverUrl, String[] protocols) {
        this(name, receiverUrl);

        for(int i = 0; i < protocols.length; i++) {
            mProtocols.add(protocols[i]);
        }
    }

    public App(String name, String receiverUrl) {
        mName = name;
        mReceiverUrl = receiverUrl;
        mState = "stopped";
        mLink = "";
        mConnectionSvcURL = "";
        mProtocols = new ArrayList<String>();

        mRemotes = new ArrayList<SessionSocket>();
        mReceivers = new ArrayList<ReceiverSocket>();
        mMessageBuffer = new LinkedList<String>();
    }

    public String getProtocols() {
        if(mState == "stopped")
            return "";
        else {
            String p = "";
            for(String proto : mProtocols) {
                p += String.format("<protocol>%s</protocol>", proto);
            }
            return p;
        }
    }

    public String getState() {
        return mState;
    }

    public void setState(String mState) {
        this.mState = mState;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String mLink) {
        this.mLink = mLink;
    }

    public String getConnectionSvcURL() {
        return mConnectionSvcURL;
    }

    public void setConnectionSvcURL(String svc) {
        mConnectionSvcURL = svc;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void addProtocol(String protocol) {
        mProtocols.add(protocol);
    }

    public String getReceiverUrl() {
        return mReceiverUrl;
    }

    public void setReceiverUrl(String mReceiverUrl) {
        this.mReceiverUrl = mReceiverUrl;
    }

    public void stop() {
        mState = "stopped";
        mConnectionSvcURL = "";
        mLink = "";
        mMessageBuffer.clear();
    }

    public ConnectionSocket getControlChannel() {
        return mControlChannel;
    }

    public void setControlChannel(ConnectionSocket mControlChannel) {
        this.mControlChannel = mControlChannel;
    }

    public void addReceiver(ReceiverSocket receiver) {
        mReceivers.add(receiver);
    }

    public void removeReceiver(ReceiverSocket receiver) {
        mReceivers.remove(receiver);
    }

    public void addRemote(SessionSocket session) {
        mRemotes.add(session);
    }

    public void removeRemote(SessionSocket session) {
        mRemotes.remove(session);
    }

    public LinkedList<String> getMessageBuffer() {
        return mMessageBuffer;
    }

    public SessionSocket getRemote() {
        if(mRemotes.size() == 0)
            return null;
        else
            return mRemotes.get(0);
    }

    public ReceiverSocket getReceiver() {
        if(mReceivers.size() == 0)
            return null;
        else
            return mReceivers.get(0);
    }
}