package io.github.izdwuut.yarl.model.factories;

import java.util.HashMap;

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
	HashMap<K, V> flyweights;
	
	public FlyweightFactory() {
		flyweights = new HashMap<K, V>();
	}
	
	/**
	 * Merges a provided entity with an underlying flyweight 
	 * accessed using a provided key.
	 * 
	 * @param key a flyweight's key
	 * @param entity an entity that is about to be merged with a flyweight
	 * 
	 * @return a merged entity
	 */
	protected V mergeFlyweight(K key, V entity) {
		for(Component comp : flyweights.get(key).getComponents()) {
			entity.add(comp);
		}
		
		return entity;
	}

	/**
	 * Assigns a provided key to a flyweight entity.
	 * 
	 * @param key a flyweight's key
	 * @param entity a flyweight entity
	 */
	protected void addFlyweight(K key, V entity) {
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
}
