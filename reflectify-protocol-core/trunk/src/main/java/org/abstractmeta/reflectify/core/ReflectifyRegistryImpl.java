/**
 * Copyright 2011 Adrian Witas
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.abstractmeta.reflectify.core;

import org.abstractmeta.reflectify.Reflectify;
import org.abstractmeta.reflectify.ReflectifyRegistry;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides default implementation for the  reflectify registry.
 *
 * @author Adrian Witas
 */
public class ReflectifyRegistryImpl implements ReflectifyRegistry {

    private final Map<Class, Reflectify> registry;


    public ReflectifyRegistryImpl() {
        this(new HashMap<Class, Reflectify>());
    }

    protected ReflectifyRegistryImpl(Map<Class, Reflectify> registry) {
        this.registry = registry;
    }

    @Override
    public void register(Reflectify reflectifyProtocol) {
        registry.put(reflectifyProtocol.getType(), reflectifyProtocol);
    }

    @Override
    public void registerAll(Collection<Reflectify> reflectify) {
        for (Reflectify protocol : reflectify) {
            register(protocol);
        }
    }

    @Override
    public boolean isRegistered(Class type) {
        return registry.containsKey(type);
    }

    @Override
    public void unregister(Class type) {
        registry.remove(type);
    }

    @Override
    public void unregisterAll() {
        registry.clear();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Reflectify<T> get(Class<T> type) {
        return (Reflectify<T>) registry.get(type);
    }

    @Override
    public Map<Class, Reflectify> getRegistry() {
        return registry;
    }


}
