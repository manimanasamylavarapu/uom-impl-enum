/**
 *  Unit-API - Units of Measurement API for Java
 *  Copyright (c) 2005-2014, Jean-Marie Dautelle, Werner Keil, V2COM.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 *    and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of JSR-363 nor the names of its contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package tec.uom.impl.enums.unit;

import static tec.uom.impl.enums.unit.Constants.*;
import tec.uom.impl.enums.AbstractQuantityFactory;
import tec.uom.impl.enums.DescriptiveEnum;
import tec.uom.impl.enums.function.DoubleFactorSupplier;
import tec.uom.impl.enums.quantity.SimpleDimension;

import java.util.HashMap;
import java.util.Map;

import javax.measure.Dimension;
import javax.measure.IncommensurableException;
import javax.measure.Quantity;
import javax.measure.UnconvertibleException;
import javax.measure.Unit;
import javax.measure.UnitConverter;
import javax.measure.quantity.InformationRate;

/**
 * Implements the speed of data-transmission. The system unit for this quantity is "bit/s" (bit per second).
 * @author Werner Keil
 * @version 1.8.1 ($Revision: 444 $), $Date: 2014-03-18 23:55:19 +0100 (Di, 18 Mär 2014) $
 */
public enum BitRateUnit implements Unit<InformationRate>, DoubleFactorSupplier, DescriptiveEnum<BitRateUnit> {
	bps("bps", BPS_NAME, 1.0), Kbps("Kbps", KBPS_NAME, 1.0e3), Mbps("Mbps", MBPS_NAME, 1.0e6),
    Gbps("Gbps", GBPS_NAME, 1.0e9), Tbps("Tbps", TBPS_NAME, 1.0e12), Pbps("Pbps", PBPS_NAME, 1.0e15), Ebps("Ebps", EBPS_NAME, 1.0e18),
    K("K", KBPS_NAME, 1.0e3), M("M", MBPS_NAME, 1.0e6), G("G", GBPS_NAME, 1.0e9), T("T", TBPS_NAME, 1.0e12), P("P", PBPS_NAME, 1.0e15),
    E("E", EBPS_NAME, 1.0e18), // aliases
    NONE("", "", 0);

	private final String symbol;
    private final String description;
    private final double multFactor;

    private BitRateUnit(final String symbol, final String name, double multF) {
        this.symbol = symbol;
    	this.description = name;
        this.multFactor = multF;
    }

    
    public String getSymbol() {
        return symbol;
    }

    public double getFactor() {
        return multFactor;
    }

    
	public Unit<InformationRate> getSystemUnit() {
		return bps;
    }

	
	public String getName() {
		return name();
	}
	
    public static BitRateUnit getBySymbol(String symbol) {
        for (BitRateUnit b : values()) {
            if (b.getSymbol().equalsIgnoreCase(symbol)) return b;
        }
        return bps;
    }

    
    public Map<Unit<?>, Integer> getProductUnits() {
        Map<Unit<?>, Integer> prodUnits = new HashMap<Unit<?>, Integer>();
        prodUnits.put(Kbps, Integer.valueOf(3));
        prodUnits.put(Mbps, Integer.valueOf(6));
        prodUnits.put(Gbps, Integer.valueOf(9));
        prodUnits.put(Tbps, Integer.valueOf(12));
        prodUnits.put(Pbps, Integer.valueOf(15));
        prodUnits.put(Ebps, Integer.valueOf(18));
        return prodUnits;
    }

    
    public Unit<InformationRate> shift(double offset) {
        return this;
    }

    
    public Unit<InformationRate> alternate(String symbol) {
        if (Kbps.name().equals(symbol))
            return K;
        if (Mbps.name().equals(symbol))
            return M;
        if (Gbps.name().equals(symbol))
            return G;
        if (Tbps.name().equals(symbol))
            return T;
        if (Pbps.name().equals(symbol))
            return P;
        if (Ebps.name().equals(symbol))
            return E;

        // and reverse
        if (K.name().equals(symbol))
            return Kbps;
        if (M.name().equals(symbol))
            return Mbps;
        if (G.name().equals(symbol))
            return Gbps;
        if (T.name().equals(symbol))
            return Tbps;
        if (P.name().equals(symbol))
            return Pbps;
        if (E.name().equals(symbol))
            return Ebps;

        return this;
    }

    @SuppressWarnings("unchecked")
	
    public <T extends Quantity<T>> Unit<T> asType(Class<T> type)
            throws ClassCastException {
        Unit<T> metricUnit = AbstractQuantityFactory.getInstance(type).getMetricUnit();
        if ((metricUnit == null) || metricUnit.isCompatible(this))
         return (Unit<T>) this;
          throw new ClassCastException("The unit: " + this //$NON-NLS-1$
              + " is not of parameterized type " + type); //$NON-NLS-1$
    }

    
    public Unit<InformationRate> divide(double divisor) {
        return this;
    }

    public Unit<?> divide(Unit<?> that) {
        return this;
    }

    public UnitConverter getConverterTo(Unit<InformationRate> that)
            throws UnconvertibleException {
        // currently unused
        return null;
    }

    public UnitConverter getConverterToAny(Unit<?> that)
            throws IncommensurableException, UnconvertibleException {
        // currently unused
        return null;
    }

    public Dimension getDimension() {
        return SimpleDimension.INSTANCE;
    }

     public Unit<?> inverse() {
        return this;
    }

    public boolean isCompatible(Unit<?> that) {
        if (that instanceof BitRateUnit) return true;
        return false;
    }

    public Unit<InformationRate> multiply(double factor) {
        return this;
    }

    public Unit<?> multiply(Unit<?> that) {
        return this;
    }

    public Unit<?> pow(int n) {
        return this;
    }

    public Unit<?> root(int n) {
        return this;
    }

    public Unit<InformationRate> transform(UnitConverter operation) {
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DescriptiveEnum<BitRateUnit>[] iValues() {
		return BitRateUnit.values();
	}
}
