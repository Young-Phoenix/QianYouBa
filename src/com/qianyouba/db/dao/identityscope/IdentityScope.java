package com.qianyouba.db.dao.identityscope;

public abstract interface IdentityScope<K, T>
{
  public abstract void clear();
  
  public abstract boolean detach(K paramK, T paramT);
  
  public abstract T get(K paramK);
  
  public abstract T getNoLock(K paramK);
  
  public abstract void lock();
  
  public abstract void put(K paramK, T paramT);
  
  public abstract void putNoLock(K paramK, T paramT);
  
  public abstract void remove(Iterable<K> paramIterable);
  
  public abstract void remove(K paramK);
  
  public abstract void reserveRoom(int paramInt);
  
  public abstract void unlock();
}


/* Location:           D:\decompiler\dex2jar-2.0\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.identityscope.IdentityScope
 * JD-Core Version:    0.7.0.1
 */