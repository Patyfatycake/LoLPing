package mayuso.LoLPing;

/**
 * Created by Lewis Kwong on 26/03/2016.
 */
public enum LoLServers {
    BR("8.23.24.100"), EUW("riot.de"), EUNE("riot.pl"), NA("riot.us"), OCE("203.174.139.185");
/* LAN("66.151.33.33"), LAS("200.73.71.100")*/

    private String ipAddress;

    LoLServers(String ip) {
        ipAddress = ip;
    }

    public String getIp() {
        return ipAddress;
    }
}
