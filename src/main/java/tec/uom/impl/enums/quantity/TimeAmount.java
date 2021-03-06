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
package tec.uom.impl.enums.quantity;

import static java.lang.Double.NaN;
import tec.uom.impl.enums.AbstractQuantity;
import tec.uom.impl.enums.format.UnitStyle;
import tec.uom.impl.enums.unit.TimeUnit;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Time;

/**
 * @author Werner Keil
 * @version 0.6.2, $Date: 2014-11-02 $
 */
public class TimeAmount extends AbstractQuantity<Time> implements Time {
   private final double scalar; // value in reference unit

   private final Double value; // value in unit (Unit unit)

   private final TimeUnit unit;

    public TimeAmount(Double val, TimeUnit un) {
        value = val;
        unit = un;
        if (val!= null && un != null) {
        	scalar = val.doubleValue() * un.getFactor();
        } 
        else scalar = NaN;        
    }

    
    public boolean isZero() {
        return (value != null) && 0d==(value.doubleValue());
    }

    public TimeAmount add(TimeAmount d1) {
        final TimeAmount dn = new TimeAmount(Double.valueOf(this.value.doubleValue() + d1.value.doubleValue()),
        		this.unit);
        return dn;
    }

    public TimeAmount subtract(TimeAmount d1) {
    	final TimeAmount dn = new TimeAmount(this.value.doubleValue() - d1.value.doubleValue(), this.unit);
        return dn;
    }

    public boolean eq(TimeAmount dq) {
         return dq!=null && dq.getValue().equals(getValue()) && dq.getUnit().equals(getUnit()) &&
                 dq.getScalar().equals(getScalar());
    }

    public boolean ne(TimeAmount d1) {
        return ne((TimeAmount) d1);
    }

    public boolean gt(TimeAmount d1) {
        return gt((TimeAmount) d1);
    }

    public boolean lt(TimeAmount d1) {
        return lt((TimeAmount) d1);
    }

    public boolean ge(TimeAmount d1) {
        return ge((TimeAmount)d1);
    }

    public boolean le(TimeAmount d1) {
        return le((TimeAmount) d1);
    }

    public TimeAmount divide(Double v) {
        return new TimeAmount(value.doubleValue() / v.doubleValue(), unit);
    }

    protected TimeAmount convert(TimeUnit newUnit) {
        return new TimeAmount(value.doubleValue() * (this.unit.getFactor() / newUnit.getFactor()), newUnit);
    }

    
    public Number getScalar() {
        return scalar;
    }

    
    public String toString(boolean withUnit, boolean withSpace, int precision) {
        final StringBuilder sb = new StringBuilder();
    	sb.append(getValue());
    	if(withUnit) {
        	if(withSpace) sb.append(" ");
    		sb.append(getUnit().getSymbol());
    	}
    	return sb.toString();
    }

    
    public String showInUnit(Unit<?> u, int precision, UnitStyle style) {
        return showInUnit(u, value, precision, style);
    }

	
	public Number getValue() {
		 return value;
	}

	
	public Unit<Time> getUnit() {
		 return unit;
	}

	
	public Quantity<Time> multiply(Number that) {
		return new TimeAmount(value.doubleValue() * that.doubleValue(), unit);
	}

	
	public Quantity<Time> to(Unit<Time> unit) {
		if (unit instanceof TimeUnit) {
        	return convert((TimeUnit)unit);
        } else {
        	throw new ArithmeticException("Cannot convert " + this.unit + " to " + unit);
        }
	}

	public boolean eq(AbstractQuantity<Time> dq) {
		 return eq((TimeAmount) dq);
	}

	
	public Quantity<?> divide(Quantity<?> that) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Quantity<Time> subtract(Quantity<Time> that) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Quantity<Time> add(Quantity<Time> that) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Quantity<Time> divide(Number that) {
		// TODO Auto-generated method stub
		return null;
	}

	public Quantity<Time> inverse() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Quantity<?> multiply(Quantity<?> that) {
		return new TimeAmount(value.doubleValue() * that.getValue().doubleValue(), unit);
	}

	public int compareTo(Quantity<Time> o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
