package br.com.meinz.agentms.config;

import br.com.meinz.agentms.enums.FunctionType;
import br.com.meinz.agentms.service.function.FunctionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class StrategyConfig {

    //TODO: Implement the method to return a map of FunctionType and FunctionStrategy
    @Bean
    public Map<FunctionType, FunctionStrategy<?,?>> functionStrategyMap() {
        return null;
    }
}
