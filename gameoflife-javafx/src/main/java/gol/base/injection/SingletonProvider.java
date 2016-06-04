package gol.base.injection;

import java.util.function.Function;

/**
 * Created by Tino on 02.06.2016.
 */
class SingletonProvider implements BeanProvider {

    private final Function<BeanRepository, ?> creator;
    private Object instance;

    SingletonProvider(final Function<BeanRepository, ?> creator) {
        this.creator = creator;
    }

    @Override public <T> T getBean(final BeanRepository repository) {
        if (instance == null) {
            instance = creator.apply(repository);
            new PostConstructor(repository).postConstruct(instance);
        }
        return (T) instance;
    }
}
