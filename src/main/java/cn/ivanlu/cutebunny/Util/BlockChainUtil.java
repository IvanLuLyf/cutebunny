package cn.ivanlu.cutebunny.Util;

import cn.ivanlu.cutebunny.Contract.FileUploadContract;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BlockChainUtil {
    private static Credentials siteCredentials = null;
    private static Map<String, Credentials> credentialsMap = null;
    private static Web3j web3j = null;
    private static final BigDecimal TEN18 = new BigDecimal("1000000000000000000");

    private static Map<String, Credentials> getCredentialsMap() {
        if (credentialsMap == null) {
            credentialsMap = new HashMap<>();
        }
        return credentialsMap;
    }

    private static Credentials getSiteCredentials() {
        if (siteCredentials == null) {
            try {
                siteCredentials = WalletUtils.loadCredentials("cutebunny", "site.json");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return siteCredentials;
    }

    public static boolean payTo(String address) {
        try {
            Credentials credentials = getSiteCredentials();
            if (credentials != null) {
                TransactionReceipt pay = Transfer.sendFunds(getWeb3j(), credentials, address, new BigDecimal("0.1"), Convert.Unit.ETHER).send();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getBalance(String address) {
        try {
            EthGetBalance ethGetBalance = getWeb3j().ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
            BigDecimal wei = new BigDecimal(ethGetBalance.getBalance());
            BigDecimal balance = wei.divide(TEN18, 10, BigDecimal.ROUND_UP);
            return balance.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    private static Web3j getWeb3j() {
        if (web3j == null) {
            web3j = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/47788e47975644b49657a044ad9c5a8a"));
        }
        return web3j;
    }

    public static Credentials login(String path, String username, String password) {
        File file = new File(path + username + ".json");
        Credentials credentials = null;
        if (null != username && !"".equals(username) && file.exists()) {
            try {
                credentials = WalletUtils.loadCredentials(password, file);
                if (credentials != null) {
                    getCredentialsMap().put(username, credentials);
                }
                return credentials;
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static Credentials register(String path, String username, String password) {
        File file = new File(path + username + ".json");
        Credentials credentials = null;
        if (null != username && !"".equals(username)) {
            if (!file.exists()) {
                try {
                    String fileName = WalletUtils.generateNewWalletFile(password, new File(path), false);
                    File oldFile = new File(path + fileName);
                    oldFile.renameTo(file);
                    credentials = WalletUtils.loadCredentials(password, file);
                    if (credentials != null) {
                        getCredentialsMap().put(username, credentials);
                    }
                    return credentials;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Credentials getUser(String username) {
        return getCredentialsMap().getOrDefault(username, null);
    }

    public static FileUploadContract getContract(String username) {
        return FileUploadContract.load("0x67a43a6bd00b356384acc8dcc8f8decda4720474", getWeb3j(), getUser(username), ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
    }
}
