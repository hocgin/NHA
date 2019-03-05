package in.hocg.message.boss.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import in.hocg.message.boss.module.service.TestService;

/**
 * Created by hocgin on 2019/3/2.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TestService.class).in(Scopes.SINGLETON);
    }
}
