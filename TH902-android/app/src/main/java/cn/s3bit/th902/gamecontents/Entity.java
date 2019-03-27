package cn.s3bit.th902.gamecontents;

import com.badlogic.gdx.utils.Pool;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.LinkedBlockingQueue;

import cn.s3bit.th902.GameMain;
import cn.s3bit.th902.gamecontents.components.Component;

public class Entity {
    public static TreeSet<Entity> instances = new TreeSet<>(new Comparator<Entity>() {

        @Override
        public int compare(Entity o1, Entity o2) {
            if (o1 == o2)
                return 0;
            if (o1.updateOrder != o2.updateOrder)
                return compare(o1.updateOrder, o2.updateOrder);
            return compare(o1.identity, o2.identity);
        }

        int compare(int x, int y) {
            return (x < y) ? -1 : ((x == y) ? 0 : 1);
        }

        int compare(long x, long y) {
            return (x < y) ? -1 : ((x == y) ? 0 : 1);
        }
    });
    public static Pool<Entity> entityPool = new Pool<Entity>(512) {
        @Override
        protected Entity newObject() {
            return new Entity();
        }
    };

    public static LinkedBlockingQueue<Runnable> postUpdate = new LinkedBlockingQueue<>();

    private TreeMap<Class<?>, Component> mComponents;
    public int updateOrder;
    public final long identity;
    public static long identitySender = 0;
    private LinkedBlockingQueue<Class<?>> toDel;

    private Entity() {
        identity = ++identitySender;
    }

    public static Entity Create() {
        return Create(0);
    }

    public static Entity Create(int updateOrder) {
        final Entity entity = entityPool.obtain(); // new Entity();
        entity.mComponents = new TreeMap<>(new Comparator<Class<?>>() {

            @Override
            public int compare(Class<?> o1, Class<?> o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        entity.toDel = new LinkedBlockingQueue<>();
        entity.updateOrder = updateOrder;
        postUpdate.add(new Runnable() {
            @Override
            public void run() {
                instances.add(entity);
            }
        });
        return entity;
    }

    public void Update() {
        Object[] entries = mComponents.entrySet().toArray();
        for (Object obj : entries) {
            @SuppressWarnings("unchecked")
            Entry<Class<?>, Component> entry = (Entry<Class<?>, Component>) obj;
            entry.getValue().Update();
            if (entry.getValue().isDead()) {
                toDel.add(entry.getKey());
            }
        }
        while (!toDel.isEmpty()) {
            mComponents.remove(toDel.poll());
        }
        if (mComponents.isEmpty()) {
            this.Destroy();
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T GetComponent(Class<T> type) {
        T c = (T) mComponents.get(type);
        if (c != null)
            return c;
        for (Class<?> typec : mComponents.keySet()) {
            if (type.isAssignableFrom(typec)) {
                return (T) mComponents.get(typec);
            }
        }
        return null;
    }

    public void AddComponent(Component component) {
        if (component == null)
            throw new NullPointerException();
        Class<?> type = component.getClass();
        if (mComponents.containsKey(type))
            throw new IllegalArgumentException();
        mComponents.put(type, component);
        component.Initialize(this);
    }

    public void Destroy() {
        Component[] components = (Component[]) mComponents.values().toArray(new Component[mComponents.values().size()]);
        for (Component component : components)
            if (!component.isDead()) {
                component.Kill();
            }
        mComponents.clear();
        // entityPool.free(this);
        instances.remove(this);
    }

    public static void Reset() {
        Entity[] entities = (Entity[]) instances.toArray(new Entity[instances.size()]);
        for (int i = 0; i < entities.length; i++) {
            entities[i].Destroy();
        }
    }

    public static void UpdateAll() {
        GameMain.instance.activeStage.act(1f / 60f);
        Entity[] entities = (Entity[]) instances.toArray(new Entity[instances.size()]);
        for (int i = 0; i < entities.length; i++) {
            entities[i].Update();
        }
        while (!postUpdate.isEmpty()) {
            postUpdate.poll().run();
        }
    }
}
