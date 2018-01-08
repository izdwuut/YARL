package io.github.izdwuut.yarl.model.factories;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

/**
 * A generic flyweight factory. Separates common {@link com.badlogic.ashley.core.Entity Entity}'s 
 * {@link com.badlogic.ashley.core.Component Components}.
 * Implements lo and behold, the flyweight design pattern.
 * 
 * @author Bartosz "izdwuut" Konikiewicz
 * @since  2017-12-28
 * 
 * @param <K> a flyweight key type
 * @param <V> a flyweight object type (extends {@link com.badlogic.ashley.core.Entity Entity})
 */
public abstract class FlyweightFactory<K, V extends Entity> {
	/**
	 * Flyweights storage.
	 */
	Map<K, V> flyweights;
	
	/**
	 * A class of underlying flyweight objects.
	 */
	Class<V> cls;
	
	public FlyweightFactory(Class<V> cls) {
		this.cls = cls;
		flyweights = new HashMap<K, V>();
	}

	/**
	 * Assigns a provided key to a flyweight entity.
	 * 
	 * @param key a flyweight's key
	 * @param entity a flyweight entity
	 */
	void addFlyweight(K key, V entity) {
		flyweights.put(key, entity);
	}
	
	/**
	 * Checks if a provided key references a flyweight.
	 * 
	 * @param key a flyweight's key
	 * 
	 * @return true if a flyweight for a given key exists, false otherwise
	 */
	protected boolean hasFlyweight(K key) {
		return flyweights.containsKey(key);
	}
	
	/**
	 * Gets an entity merged with provided components.
	 * Creates a flyweight object using provided components.
	 * 
	 * @param key a flyweight's key
	 * @param entity the entity with custom components
	 * @param components components that are about to be merged with the entity
	 * 
	 * @return a merged entity
	 */
	protected V getEntity(K key, V entity, Component... components) {	
		try {				
			V flyweight = cls.newInstance();
			addFlyweight(key, flyweight);
			for(Component comp : components) {
				flyweight.add(comp);
				entity.add(comp);
			}
		} catch(Exception e) {
			for(Component comp : components) {
				entity.add(comp);
			}
			System.out.println("This should never happen: " + e);
		}
		
		return entity;
	}
	
	/**
	 * Gets an entity merged with an underlying flyweight object.
	 * 
	 * @param key a flyweight's key
	 * @param entity an entity with custom components
	 * 
	 * @return the entity with flyweight components
	 */
	protected V getEntity(K key, V entity) {	
		for(Component comp : getFlyweight(key).getComponents()) {
			entity.add(comp);
		}
		
		return entity;
	}
	
	/**
	 * Gets a flyweight that corresponds to a provided key.
	 * 
	 * @param key a key assigned to a flyweight
	 * 
	 * @return a flyweight object if a key exists, null otherwise
	 */
	V getFlyweight(K key) {
		return flyweights.get(key);
	}
}
