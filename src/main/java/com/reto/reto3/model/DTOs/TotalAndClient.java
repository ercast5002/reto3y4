package com.reto.reto3.model.DTOs;

import ch.qos.logback.core.net.server.Client;

public class TotalAndClient {
    private long total;
    private Client client;

    public TotalAndClient(long total, Client client) {
        this.total = total;
        this.client = client;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
