package cn.ivanlu.cutebunny.Util;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;

import java.io.IOException;
import java.util.List;

public class IPFSUtil {
    private static IPFS ipfs;

    public static IPFS getIpfs() {
        if (ipfs == null) {
            ipfs = new IPFS("ipfs.infura.io", 5001, true);
        }
        return ipfs;
    }

    public static String uploadFile(byte[] content) {
        NamedStreamable.ByteArrayWrapper wrapper = new NamedStreamable.ByteArrayWrapper(content);
        try {
            List<MerkleNode> result = getIpfs().add(wrapper);
            return result.get(0).hash.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
