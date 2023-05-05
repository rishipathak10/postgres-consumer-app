package com.example.postgresdemo.model;

public class Credentials {
    private String host;
    private String password;
    private long port;
    private long serviceGatewayAccessPort;
    private boolean serviceGatewayEnabled;

    public String getHost() {
        return host;
    }

    public void setHost(String value) {
        this.host = value;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    public long getPort() {
        return port;
    }

    public void setPort(long value) {
        this.port = value;
    }

    public long getServiceGatewayAccessPort() {
        return serviceGatewayAccessPort;
    }

    public void setServiceGatewayAccessPort(long value) {
        this.serviceGatewayAccessPort = value;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "host='" + host + '\'' +
                ", password='" + password + '\'' +
                ", port=" + port +
                ", serviceGatewayAccessPort=" + serviceGatewayAccessPort +
                ", serviceGatewayEnabled=" + serviceGatewayEnabled +
                '}';
    }

    public boolean getServiceGatewayEnabled() {
        return serviceGatewayEnabled;
    }

    public void setServiceGatewayEnabled(boolean value) {
        this.serviceGatewayEnabled = value;
    }



}
