package com.main.daapdroid;

import javax.jmdns.JmDNS;

import android.content.pm.ServiceInfo;

public class Server {
	/*
	 * javax.jmdns.ServiceInfo a = event.getInfo();
			String b = event.getName();
			String c = event.getType();
			JmDNS d  = event.getDNS();
	 */
	
	private String serverName;
	private String serverType;
	private javax.jmdns.ServiceInfo serverInfo;
	private JmDNS serverDNS;
	
	Server(String serverName,String serverType,javax.jmdns.ServiceInfo serverInfo,JmDNS serverDNS)
	{
		this.serverName = serverName;
		this.serverType = serverType;
		this.serverInfo = serverInfo;
		this.serverDNS = serverDNS;
	}
	
    
    public String getServerName() {
        return serverName;
    }
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
    
    public String getServerType() {
        return serverType;
    }
    public void setServerAdress(String serverType) {
        this.serverType = serverType;
    }
    
    public javax.jmdns.ServiceInfo getServerInfo() {
        return serverInfo;
    }
    public void setServerInfo(javax.jmdns.ServiceInfo serverInfo) {
        this.serverInfo = serverInfo;
    }
    
    public JmDNS getServerDNS() {
        return serverDNS;
    }
    public void setServerDNS(JmDNS serverDNS) {
        this.serverDNS = serverDNS;
    }

}
