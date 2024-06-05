package cdm.product.asset;

import cdm.base.datetime.Period;
import cdm.observable.asset.ExchangeRate;
import cdm.product.asset.ForeignExchange;
import cdm.product.asset.ForeignExchange.ForeignExchangeBuilder;
import cdm.product.asset.ForeignExchange.ForeignExchangeBuilderImpl;
import cdm.product.asset.ForeignExchange.ForeignExchangeImpl;
import cdm.product.asset.meta.ForeignExchangeMeta;
import cdm.product.common.settlement.Cashflow;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * From FpML: A type defining either a spot or forward FX transactions.
 * @version ${project.version}
 */
@RosettaDataType(value="ForeignExchange", builder=ForeignExchange.ForeignExchangeBuilderImpl.class, version="${project.version}")
public interface ForeignExchange extends RosettaModelObject {

	ForeignExchangeMeta metaData = new ForeignExchangeMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * This is the first of the two currency flows that define a single leg of a standard foreign exchange transaction.
	 */
	Cashflow getExchangedCurrency1();
	/**
	 * This is the second of the two currency flows that define a single leg of a standard foreign exchange transaction.
	 */
	Cashflow getExchangedCurrency2();
	/**
	 * A tenor expressed as a period type and multiplier (e.g. 1D, 1Y, etc.)
	 */
	Period getTenorPeriod();
	/**
	 * The rate of exchange between the two currencies.
	 */
	ExchangeRate getExchangeRate();

	/*********************** Build Methods  ***********************/
	ForeignExchange build();
	
	ForeignExchange.ForeignExchangeBuilder toBuilder();
	
	static ForeignExchange.ForeignExchangeBuilder builder() {
		return new ForeignExchange.ForeignExchangeBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ForeignExchange> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ForeignExchange> getType() {
		return ForeignExchange.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("exchangedCurrency1"), processor, Cashflow.class, getExchangedCurrency1());
		processRosetta(path.newSubPath("exchangedCurrency2"), processor, Cashflow.class, getExchangedCurrency2());
		processRosetta(path.newSubPath("tenorPeriod"), processor, Period.class, getTenorPeriod());
		processRosetta(path.newSubPath("exchangeRate"), processor, ExchangeRate.class, getExchangeRate());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ForeignExchangeBuilder extends ForeignExchange, RosettaModelObjectBuilder {
		Cashflow.CashflowBuilder getOrCreateExchangedCurrency1();
		Cashflow.CashflowBuilder getExchangedCurrency1();
		Cashflow.CashflowBuilder getOrCreateExchangedCurrency2();
		Cashflow.CashflowBuilder getExchangedCurrency2();
		Period.PeriodBuilder getOrCreateTenorPeriod();
		Period.PeriodBuilder getTenorPeriod();
		ExchangeRate.ExchangeRateBuilder getOrCreateExchangeRate();
		ExchangeRate.ExchangeRateBuilder getExchangeRate();
		ForeignExchange.ForeignExchangeBuilder setExchangedCurrency1(Cashflow exchangedCurrency1);
		ForeignExchange.ForeignExchangeBuilder setExchangedCurrency2(Cashflow exchangedCurrency2);
		ForeignExchange.ForeignExchangeBuilder setTenorPeriod(Period tenorPeriod);
		ForeignExchange.ForeignExchangeBuilder setExchangeRate(ExchangeRate exchangeRate);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("exchangedCurrency1"), processor, Cashflow.CashflowBuilder.class, getExchangedCurrency1());
			processRosetta(path.newSubPath("exchangedCurrency2"), processor, Cashflow.CashflowBuilder.class, getExchangedCurrency2());
			processRosetta(path.newSubPath("tenorPeriod"), processor, Period.PeriodBuilder.class, getTenorPeriod());
			processRosetta(path.newSubPath("exchangeRate"), processor, ExchangeRate.ExchangeRateBuilder.class, getExchangeRate());
		}
		

		ForeignExchange.ForeignExchangeBuilder prune();
	}

	/*********************** Immutable Implementation of ForeignExchange  ***********************/
	class ForeignExchangeImpl implements ForeignExchange {
		private final Cashflow exchangedCurrency1;
		private final Cashflow exchangedCurrency2;
		private final Period tenorPeriod;
		private final ExchangeRate exchangeRate;
		
		protected ForeignExchangeImpl(ForeignExchange.ForeignExchangeBuilder builder) {
			this.exchangedCurrency1 = ofNullable(builder.getExchangedCurrency1()).map(f->f.build()).orElse(null);
			this.exchangedCurrency2 = ofNullable(builder.getExchangedCurrency2()).map(f->f.build()).orElse(null);
			this.tenorPeriod = ofNullable(builder.getTenorPeriod()).map(f->f.build()).orElse(null);
			this.exchangeRate = ofNullable(builder.getExchangeRate()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("exchangedCurrency1")
		public Cashflow getExchangedCurrency1() {
			return exchangedCurrency1;
		}
		
		@Override
		@RosettaAttribute("exchangedCurrency2")
		public Cashflow getExchangedCurrency2() {
			return exchangedCurrency2;
		}
		
		@Override
		@RosettaAttribute("tenorPeriod")
		public Period getTenorPeriod() {
			return tenorPeriod;
		}
		
		@Override
		@RosettaAttribute("exchangeRate")
		public ExchangeRate getExchangeRate() {
			return exchangeRate;
		}
		
		@Override
		public ForeignExchange build() {
			return this;
		}
		
		@Override
		public ForeignExchange.ForeignExchangeBuilder toBuilder() {
			ForeignExchange.ForeignExchangeBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ForeignExchange.ForeignExchangeBuilder builder) {
			ofNullable(getExchangedCurrency1()).ifPresent(builder::setExchangedCurrency1);
			ofNullable(getExchangedCurrency2()).ifPresent(builder::setExchangedCurrency2);
			ofNullable(getTenorPeriod()).ifPresent(builder::setTenorPeriod);
			ofNullable(getExchangeRate()).ifPresent(builder::setExchangeRate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ForeignExchange _that = getType().cast(o);
		
			if (!Objects.equals(exchangedCurrency1, _that.getExchangedCurrency1())) return false;
			if (!Objects.equals(exchangedCurrency2, _that.getExchangedCurrency2())) return false;
			if (!Objects.equals(tenorPeriod, _that.getTenorPeriod())) return false;
			if (!Objects.equals(exchangeRate, _that.getExchangeRate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (exchangedCurrency1 != null ? exchangedCurrency1.hashCode() : 0);
			_result = 31 * _result + (exchangedCurrency2 != null ? exchangedCurrency2.hashCode() : 0);
			_result = 31 * _result + (tenorPeriod != null ? tenorPeriod.hashCode() : 0);
			_result = 31 * _result + (exchangeRate != null ? exchangeRate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ForeignExchange {" +
				"exchangedCurrency1=" + this.exchangedCurrency1 + ", " +
				"exchangedCurrency2=" + this.exchangedCurrency2 + ", " +
				"tenorPeriod=" + this.tenorPeriod + ", " +
				"exchangeRate=" + this.exchangeRate +
			'}';
		}
	}

	/*********************** Builder Implementation of ForeignExchange  ***********************/
	class ForeignExchangeBuilderImpl implements ForeignExchange.ForeignExchangeBuilder {
	
		protected Cashflow.CashflowBuilder exchangedCurrency1;
		protected Cashflow.CashflowBuilder exchangedCurrency2;
		protected Period.PeriodBuilder tenorPeriod;
		protected ExchangeRate.ExchangeRateBuilder exchangeRate;
	
		public ForeignExchangeBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("exchangedCurrency1")
		public Cashflow.CashflowBuilder getExchangedCurrency1() {
			return exchangedCurrency1;
		}
		
		@Override
		public Cashflow.CashflowBuilder getOrCreateExchangedCurrency1() {
			Cashflow.CashflowBuilder result;
			if (exchangedCurrency1!=null) {
				result = exchangedCurrency1;
			}
			else {
				result = exchangedCurrency1 = Cashflow.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("exchangedCurrency2")
		public Cashflow.CashflowBuilder getExchangedCurrency2() {
			return exchangedCurrency2;
		}
		
		@Override
		public Cashflow.CashflowBuilder getOrCreateExchangedCurrency2() {
			Cashflow.CashflowBuilder result;
			if (exchangedCurrency2!=null) {
				result = exchangedCurrency2;
			}
			else {
				result = exchangedCurrency2 = Cashflow.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("tenorPeriod")
		public Period.PeriodBuilder getTenorPeriod() {
			return tenorPeriod;
		}
		
		@Override
		public Period.PeriodBuilder getOrCreateTenorPeriod() {
			Period.PeriodBuilder result;
			if (tenorPeriod!=null) {
				result = tenorPeriod;
			}
			else {
				result = tenorPeriod = Period.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("exchangeRate")
		public ExchangeRate.ExchangeRateBuilder getExchangeRate() {
			return exchangeRate;
		}
		
		@Override
		public ExchangeRate.ExchangeRateBuilder getOrCreateExchangeRate() {
			ExchangeRate.ExchangeRateBuilder result;
			if (exchangeRate!=null) {
				result = exchangeRate;
			}
			else {
				result = exchangeRate = ExchangeRate.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("exchangedCurrency1")
		public ForeignExchange.ForeignExchangeBuilder setExchangedCurrency1(Cashflow exchangedCurrency1) {
			this.exchangedCurrency1 = exchangedCurrency1==null?null:exchangedCurrency1.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("exchangedCurrency2")
		public ForeignExchange.ForeignExchangeBuilder setExchangedCurrency2(Cashflow exchangedCurrency2) {
			this.exchangedCurrency2 = exchangedCurrency2==null?null:exchangedCurrency2.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("tenorPeriod")
		public ForeignExchange.ForeignExchangeBuilder setTenorPeriod(Period tenorPeriod) {
			this.tenorPeriod = tenorPeriod==null?null:tenorPeriod.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("exchangeRate")
		public ForeignExchange.ForeignExchangeBuilder setExchangeRate(ExchangeRate exchangeRate) {
			this.exchangeRate = exchangeRate==null?null:exchangeRate.toBuilder();
			return this;
		}
		
		@Override
		public ForeignExchange build() {
			return new ForeignExchange.ForeignExchangeImpl(this);
		}
		
		@Override
		public ForeignExchange.ForeignExchangeBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ForeignExchange.ForeignExchangeBuilder prune() {
			if (exchangedCurrency1!=null && !exchangedCurrency1.prune().hasData()) exchangedCurrency1 = null;
			if (exchangedCurrency2!=null && !exchangedCurrency2.prune().hasData()) exchangedCurrency2 = null;
			if (tenorPeriod!=null && !tenorPeriod.prune().hasData()) tenorPeriod = null;
			if (exchangeRate!=null && !exchangeRate.prune().hasData()) exchangeRate = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getExchangedCurrency1()!=null && getExchangedCurrency1().hasData()) return true;
			if (getExchangedCurrency2()!=null && getExchangedCurrency2().hasData()) return true;
			if (getTenorPeriod()!=null && getTenorPeriod().hasData()) return true;
			if (getExchangeRate()!=null && getExchangeRate().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ForeignExchange.ForeignExchangeBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ForeignExchange.ForeignExchangeBuilder o = (ForeignExchange.ForeignExchangeBuilder) other;
			
			merger.mergeRosetta(getExchangedCurrency1(), o.getExchangedCurrency1(), this::setExchangedCurrency1);
			merger.mergeRosetta(getExchangedCurrency2(), o.getExchangedCurrency2(), this::setExchangedCurrency2);
			merger.mergeRosetta(getTenorPeriod(), o.getTenorPeriod(), this::setTenorPeriod);
			merger.mergeRosetta(getExchangeRate(), o.getExchangeRate(), this::setExchangeRate);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ForeignExchange _that = getType().cast(o);
		
			if (!Objects.equals(exchangedCurrency1, _that.getExchangedCurrency1())) return false;
			if (!Objects.equals(exchangedCurrency2, _that.getExchangedCurrency2())) return false;
			if (!Objects.equals(tenorPeriod, _that.getTenorPeriod())) return false;
			if (!Objects.equals(exchangeRate, _that.getExchangeRate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (exchangedCurrency1 != null ? exchangedCurrency1.hashCode() : 0);
			_result = 31 * _result + (exchangedCurrency2 != null ? exchangedCurrency2.hashCode() : 0);
			_result = 31 * _result + (tenorPeriod != null ? tenorPeriod.hashCode() : 0);
			_result = 31 * _result + (exchangeRate != null ? exchangeRate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ForeignExchangeBuilder {" +
				"exchangedCurrency1=" + this.exchangedCurrency1 + ", " +
				"exchangedCurrency2=" + this.exchangedCurrency2 + ", " +
				"tenorPeriod=" + this.tenorPeriod + ", " +
				"exchangeRate=" + this.exchangeRate +
			'}';
		}
	}
}
