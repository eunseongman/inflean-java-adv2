package network.tcp.autocloserable;

public class ResourceCloseMainV1 {

    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외 처리");
            throw new RuntimeException(e);
        } catch (CloseException e) {
            System.out.println("CloseException 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CallException, CloseException {
        ResourceV1 resource1 = new ResourceV1("resource1");
        ResourceV1 resource2 = new ResourceV1("resource2");

        resource1.call();
        resource2.callEx();

        System.out.println("자원 정리");
        resource2.closeEx();
        resource1.closeEx();
    }
}

// callEx() 를 호출하면서 예외가 발생했다. 예외 때문에 자원 정리 코드가 정상 호출되지 않았다.
// 이 코드는 예외가 발생하면 자원이 정리되지 않는다는 문제가 있다.
