/*************************************************************************************
 * Copyright (C) 2014 GENERAL BYTES s.r.o. All rights reserved.
 *
 * This software may be distributed and modified under the terms of the GNU
 * General Public License version 2 (GPL2) as published by the Free Software
 * Foundation and appearing in the file GPL2.TXT included in the packaging of
 * this file. Please note that GPL2 Section 2[b] requires that all works based
 * on this software must also be made publicly available under the terms of
 * the GPL2 ("Copyleft").
 *
 * Contact information
 * -------------------
 *
 * GENERAL BYTES s.r.o.
 * Web      :  http://www.generalbytes.com
 *
 * Other information:
 *
 * This implementation was created in cooperation with Orillia BVBA
 ************************************************************************************/
package com.generalbytes.batm.server.extensions.extra.bitcoin.exchanges.bitfinex;

import com.generalbytes.batm.server.extensions.ICurrencies;
import com.generalbytes.batm.server.extensions.IExchangeAdvanced;
import com.generalbytes.batm.server.extensions.IRateSource;
import com.generalbytes.batm.server.extensions.extra.bitcoin.exchanges.XChangeExchange;
import com.xeiam.xchange.ExchangeSpecification;

import java.util.HashSet;
import java.util.Set;

public class BitfinexExchange extends XChangeExchange implements IExchangeAdvanced, IRateSource {

    private static ExchangeSpecification getSpecification(String apiKey, String apiSecret) {
        ExchangeSpecification spec = new com.xeiam.xchange.bitfinex.v1.BitfinexExchange().getDefaultExchangeSpecification();
        spec.setApiKey(apiKey);
        spec.setSecretKey(apiSecret);
        return spec;
    }

    public BitfinexExchange(String apiKey, String apiSecret) {
        super(getSpecification(apiKey, apiSecret));
    }

    @Override
    public Set<String> getCryptoCurrencies() {
        Set<String> cryptoCurrencies = new HashSet<String>();
        cryptoCurrencies.add(ICurrencies.BTC);
        return cryptoCurrencies;
    }

    @Override
    public Set<String> getFiatCurrencies() {
        Set<String> fiatCurrencies = new HashSet<String>();
        fiatCurrencies.add(ICurrencies.USD);
        return fiatCurrencies;
    }

    @Override
    public String getPreferredFiatCurrency() {
        return ICurrencies.USD;
    }

    @Override
    protected boolean isWithdrawSuccessful(String result) {
        return "success".equalsIgnoreCase(result);
    }

    @Override
    protected double getAllowedCallsPerSecond() {
        return 0.5;
    }
}