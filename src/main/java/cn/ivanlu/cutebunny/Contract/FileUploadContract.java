package cn.ivanlu.cutebunny.Contract;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

import java.math.BigInteger;
import java.util.Arrays;

public class FileUploadContract extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550611621806100606000396000f30060806040526004361061006d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680631562cf45146100725780634e03126d14610154578063802fb06814610203578063a49712eb146102e5578063b57d0322146103da575b600080fd5b34801561007e57600080fd5b506100d9600480360381019080803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506104bc565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156101195780820151818401526020810190506100fe565b50505050905090810190601f1680156101465780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561016057600080fd5b50610201600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506106d6565b005b34801561020f57600080fd5b5061026a600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610c0a565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156102aa57808201518184015260208101905061028f565b50505050905090810190601f1680156102d75780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156102f157600080fd5b506103d8600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610e9b565b005b3480156103e657600080fd5b50610441600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050611194565b6040518080602001828103825283818151815260200191508051906020019080838360005b83811015610481578082015181840152602081019050610466565b50505050905090810190601f1680156104ae5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b606060008090505b60018054905081101561069757826040518082805190602001908083835b60208310151561050757805182526020820191506020810190506020830392506104e2565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206000191660018281548110151561054757fe5b906000526020600020906004020160010160405180828054600181600116156101000203166002900480156105b35780601f106105915761010080835404028352918201916105b3565b820191906000526020600020905b81548152906001019060200180831161059f575b5050915050604051809103902060001916141561068a576001818154811015156105d957fe5b90600052602060002090600402016003018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561067e5780601f106106535761010080835404028352916020019161067e565b820191906000526020600020905b81548152906001019060200180831161066157829003601f168201915b505050505091506106d0565b80806001019150506104c4565b6040805190810160405280600481526020017f6e756c6c0000000000000000000000000000000000000000000000000000000081525091505b50919050565b60008060008060019350600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000209250600091505b82805490508210156109f457856040518082805190602001908083835b6020831015156107685780518252602082019150602081019050602083039250610743565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206000191683838154811015156107a757fe5b906000526020600020906002020160000160405180828054600181600116156101000203166002900480156108135780601f106107f1576101008083540402835291820191610813565b820191906000526020600020905b8154815290600101906020018083116107ff575b505091505060405180910390206000191614156109e7576000935084600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208381548110151561087b57fe5b9060005260206000209060020201600101908051906020019061089f9291906113ef565b5085600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020838154811015156108ed57fe5b906000526020600020906002020160000190805190602001906109119291906113ef565b507f0ffba5d6f65569316d04ab3db12a4d0ceb899be7cd1a1743653174cc9d811c833387604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001828103825283818151815260200191508051906020019080838360005b838110156109a757808201518184015260208101905061098c565b50505050905090810190601f1680156109d45780820380516001836020036101000a031916815260200191505b50935050505060405180910390a16109f4565b8180600101925050610726565b8315610c0257600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208054809190600101610a4b919061146f565b905084600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002082815481101515610a9a57fe5b90600052602060002090600202016001019080519060200190610abe9291906113ef565b5085600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002082815481101515610b0c57fe5b90600052602060002090600202016000019080519060200190610b309291906113ef565b507f0ffba5d6f65569316d04ab3db12a4d0ceb899be7cd1a1743653174cc9d811c833387604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001828103825283818151815260200191508051906020019080838360005b83811015610bc6578082015181840152602081019050610bab565b50505050905090810190601f168015610bf35780820380516001836020036101000a031916815260200191505b50935050505060405180910390a15b505050505050565b606060008090505b600180549050811015610e5c573373ffffffffffffffffffffffffffffffffffffffff16600182815481101515610c4557fe5b906000526020600020906004020160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16148015610d8a5750826040518082805190602001908083835b602083101515610ccb5780518252602082019150602081019050602083039250610ca6565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060001916600182815481101515610d0b57fe5b90600052602060002090600402016002016040518082805460018160011615610100020316600290048015610d775780601f10610d55576101008083540402835291820191610d77565b820191906000526020600020905b815481529060010190602001808311610d63575b5050915050604051809103902060001916145b15610e4f57600181815481101515610d9e57fe5b90600052602060002090600402016003018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610e435780601f10610e1857610100808354040283529160200191610e43565b820191906000526020600020905b815481529060010190602001808311610e2657829003601f168201915b50505050509150610e95565b8080600101915050610c12565b6040805190810160405280600481526020017f6e756c6c0000000000000000000000000000000000000000000000000000000081525091505b50919050565b600080600060019250600091505b600180549050821015611071573373ffffffffffffffffffffffffffffffffffffffff16600183815481101515610edc57fe5b906000526020600020906004020160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161480156110215750846040518082805190602001908083835b602083101515610f625780518252602082019150602081019050602083039250610f3d565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060001916600183815481101515610fa257fe5b9060005260206000209060040201600201604051808280546001816001161561010002031660029004801561100e5780601f10610fec57610100808354040283529182019161100e565b820191906000526020600020905b815481529060010190602001808311610ffa575b5050915050604051809103902060001916145b1561106457600092508360018381548110151561103a57fe5b9060005260206000209060040201600301908051906020019061105e9291906113ef565b50611071565b8180600101925050610ea9565b821561118c576001805480919060010161108b91906114a1565b90503360018281548110151561109d57fe5b906000526020600020906004020160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550856001828154811015156110fc57fe5b906000526020600020906004020160010190805190602001906111209291906113ef565b508460018281548110151561113157fe5b906000526020600020906004020160020190805190602001906111559291906113ef565b508360018281548110151561116657fe5b9060005260206000209060040201600301908051906020019061118a9291906113ef565b505b505050505050565b6060600080600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000209150600090505b81805490508110156113af57836040518082805190602001908083835b60208310151561122157805182526020820191506020810190506020830392506111fc565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060001916828281548110151561126057fe5b906000526020600020906002020160000160405180828054600181600116156101000203166002900480156112cc5780601f106112aa5761010080835404028352918201916112cc565b820191906000526020600020905b8154815290600101906020018083116112b8575b505091505060405180910390206000191614156113a25781818154811015156112f157fe5b90600052602060002090600202016001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156113965780601f1061136b57610100808354040283529160200191611396565b820191906000526020600020905b81548152906001019060200180831161137957829003601f168201915b505050505092506113e8565b80806001019150506111df565b6040805190810160405280600581526020017f6572726f7200000000000000000000000000000000000000000000000000000081525092505b5050919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061143057805160ff191683800117855561145e565b8280016001018555821561145e579182015b8281111561145d578251825591602001919060010190611442565b5b50905061146b91906114d3565b5090565b81548183558181111561149c5760020281600202836000526020600020918201910161149b91906114f8565b5b505050565b8154818355818111156114ce576004028160040283600052602060002091820191016114cd9190611537565b5b505050565b6114f591905b808211156114f15760008160009055506001016114d9565b5090565b90565b61153491905b80821115611530576000808201600061151791906115ad565b60018201600061152791906115ad565b506002016114fe565b5090565b90565b6115aa91905b808211156115a657600080820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905560018201600061157d91906115ad565b60028201600061158d91906115ad565b60038201600061159d91906115ad565b5060040161153d565b5090565b90565b50805460018160011615610100020316600290046000825580601f106115d357506115f2565b601f0160209004906000526020600020908101906115f191906114d3565b5b505600a165627a7a72305820ee8c7f64ff43c3df4deb30049470daeda9882a04ad95278985030c9c4e559be60029";

    private FileUploadContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private FileUploadContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    /**
     * Load a Contract
     *
     * @param contractAddress
     * @param web3j
     * @param credentials
     * @param gasPrice
     * @param gasLimit
     * @return
     */
    public static FileUploadContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new FileUploadContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static FileUploadContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new FileUploadContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    /**
     * Deploy a Contract
     *
     * @param web3j
     * @param credentials
     * @param gasPrice
     * @param gasLimit
     * @return
     */
    public static RemoteCall<FileUploadContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList());
        return deployRemoteCall(FileUploadContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<FileUploadContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList());
        return deployRemoteCall(FileUploadContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    /**
     * Upload or Update a File into BlockChain
     *
     * @param filename
     * @param filehash
     * @return
     */
    public RemoteCall<TransactionReceipt> saveFile(String filename, String filehash) {
        Function function = new Function(
                "Save",
                Arrays.<Type>asList(new Utf8String(filename), new Utf8String(filehash)),
                Arrays.<TypeReference<?>>asList()
        );
        return executeRemoteCallTransaction(function);
    }

    /**
     * Get a File's hash by Filename
     *
     * @param filename
     * @return File's Hash
     */
    public RemoteCall<String> getFile(String filename) {
        Function function = new Function(
                "Get",
                Arrays.<Type>asList(new Utf8String(filename)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
                })
        );
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    /**
     * Save data to BlockChain
     *
     * @param primary unique key for query
     * @param key     for self search
     * @param data    the data to store
     * @return
     */
    public RemoteCall<TransactionReceipt> saveData(String primary, String key, String data) {
        Function function = new Function(
                "SaveData",
                Arrays.<Type>asList(new Utf8String(primary), new Utf8String(key), new Utf8String(data)),
                Arrays.<TypeReference<?>>asList()
        );
        return executeRemoteCallTransaction(function);
    }

    /**
     * Get data in BlockChain by Key
     *
     * @param key
     * @return JSON or null
     */
    public RemoteCall<String> getData(String key) {
        Function function = new Function(
                "GetData",
                Arrays.<Type>asList(new Utf8String(key)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
                })
        );
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    /**
     * Query data in BlockChain by primary key
     *
     * @param primary the primary Key
     * @return the query result
     */
    public RemoteCall<String> queryData(String primary) {
        Function function = new Function(
                "QueryData",
                Arrays.<Type>asList(new Utf8String(primary)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
                })
        );
        return executeRemoteCallSingleValueReturn(function, String.class);
    }
}
