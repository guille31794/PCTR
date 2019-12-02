/**
 * @(#)RWmonitor.java
 * @author M. Ben-Ari (adaptado)
 * @version 1.00 2006
 */

 import java.io.RandomAccessFile.*;
 import java.util.concurrent.locks.*;

public class RWFileMonitorAN
{
  private ReentrantLock lock = new ReentrantLock(true);

  void StartRead()
  {
    lock.lock();
    System.out.println("Lector inicia lectura...");
    try { Thread.currentThread().sleep(500);  } catch (Exception e) {}
  }

  void EndRead()
  {
    System.out.println("Lector finaliza lectura...");
    lock.unlock();
  }

  void StartWrite()
  {
    lock.lock();
    System.out.println("Escritor inicia escritura...");
    try { Thread.currentThread().sleep(1000);  } catch (Exception e) {}
  }

  void EndWrite()
  {
    System.out.println("Escritor finaliza escritura...");
    lock.unlock();
  }
}
