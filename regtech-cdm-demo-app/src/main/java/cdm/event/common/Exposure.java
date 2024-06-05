package cdm.event.common;

import cdm.event.common.Exposure;
import cdm.event.common.Exposure.ExposureBuilder;
import cdm.event.common.Exposure.ExposureBuilderImpl;
import cdm.event.common.Exposure.ExposureImpl;
import cdm.event.common.meta.ExposureMeta;
import cdm.event.position.PortfolioState;
import cdm.event.position.metafields.ReferenceWithMetaPortfolioState;
import cdm.event.position.metafields.ReferenceWithMetaPortfolioState.ReferenceWithMetaPortfolioStateBuilder;
import cdm.observable.asset.Money;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.time.ZonedDateTime;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Represents the current mark to market value or IM calculation value of the trade portfolio as recorded by the principle (in base currency).
 * @version ${project.version}
 */
@RosettaDataType(value="Exposure", builder=Exposure.ExposureBuilderImpl.class, version="${project.version}")
public interface Exposure extends RosettaModelObject {

	ExposureMeta metaData = new ExposureMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Represents a Portfolio that describes all the positions held at a given time, in various states which can be either traded, settled, etc., with lineage information to the previous state.
	 */
	ReferenceWithMetaPortfolioState getTradePortfolio();
	/**
	 * Represents the aggregate value of the portfolio in base currency.
	 */
	Money getAggregateValue();
	/**
	 * Indicates the date when the exposure is calculated if different from valuation date.
	 */
	ZonedDateTime getCalculationDateTime();
	/**
	 * Indicates the valuation date of the exposure underlying the calculation.
	 */
	ZonedDateTime getValuationDateTime();

	/*********************** Build Methods  ***********************/
	Exposure build();
	
	Exposure.ExposureBuilder toBuilder();
	
	static Exposure.ExposureBuilder builder() {
		return new Exposure.ExposureBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Exposure> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Exposure> getType() {
		return Exposure.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("tradePortfolio"), processor, ReferenceWithMetaPortfolioState.class, getTradePortfolio());
		processRosetta(path.newSubPath("aggregateValue"), processor, Money.class, getAggregateValue());
		processor.processBasic(path.newSubPath("calculationDateTime"), ZonedDateTime.class, getCalculationDateTime(), this);
		processor.processBasic(path.newSubPath("valuationDateTime"), ZonedDateTime.class, getValuationDateTime(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface ExposureBuilder extends Exposure, RosettaModelObjectBuilder {
		ReferenceWithMetaPortfolioState.ReferenceWithMetaPortfolioStateBuilder getOrCreateTradePortfolio();
		ReferenceWithMetaPortfolioState.ReferenceWithMetaPortfolioStateBuilder getTradePortfolio();
		Money.MoneyBuilder getOrCreateAggregateValue();
		Money.MoneyBuilder getAggregateValue();
		Exposure.ExposureBuilder setTradePortfolio(ReferenceWithMetaPortfolioState tradePortfolio0);
		Exposure.ExposureBuilder setTradePortfolioValue(PortfolioState tradePortfolio1);
		Exposure.ExposureBuilder setAggregateValue(Money aggregateValue);
		Exposure.ExposureBuilder setCalculationDateTime(ZonedDateTime calculationDateTime);
		Exposure.ExposureBuilder setValuationDateTime(ZonedDateTime valuationDateTime);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("tradePortfolio"), processor, ReferenceWithMetaPortfolioState.ReferenceWithMetaPortfolioStateBuilder.class, getTradePortfolio());
			processRosetta(path.newSubPath("aggregateValue"), processor, Money.MoneyBuilder.class, getAggregateValue());
			processor.processBasic(path.newSubPath("calculationDateTime"), ZonedDateTime.class, getCalculationDateTime(), this);
			processor.processBasic(path.newSubPath("valuationDateTime"), ZonedDateTime.class, getValuationDateTime(), this);
		}
		

		Exposure.ExposureBuilder prune();
	}

	/*********************** Immutable Implementation of Exposure  ***********************/
	class ExposureImpl implements Exposure {
		private final ReferenceWithMetaPortfolioState tradePortfolio;
		private final Money aggregateValue;
		private final ZonedDateTime calculationDateTime;
		private final ZonedDateTime valuationDateTime;
		
		protected ExposureImpl(Exposure.ExposureBuilder builder) {
			this.tradePortfolio = ofNullable(builder.getTradePortfolio()).map(f->f.build()).orElse(null);
			this.aggregateValue = ofNullable(builder.getAggregateValue()).map(f->f.build()).orElse(null);
			this.calculationDateTime = builder.getCalculationDateTime();
			this.valuationDateTime = builder.getValuationDateTime();
		}
		
		@Override
		@RosettaAttribute("tradePortfolio")
		public ReferenceWithMetaPortfolioState getTradePortfolio() {
			return tradePortfolio;
		}
		
		@Override
		@RosettaAttribute("aggregateValue")
		public Money getAggregateValue() {
			return aggregateValue;
		}
		
		@Override
		@RosettaAttribute("calculationDateTime")
		public ZonedDateTime getCalculationDateTime() {
			return calculationDateTime;
		}
		
		@Override
		@RosettaAttribute("valuationDateTime")
		public ZonedDateTime getValuationDateTime() {
			return valuationDateTime;
		}
		
		@Override
		public Exposure build() {
			return this;
		}
		
		@Override
		public Exposure.ExposureBuilder toBuilder() {
			Exposure.ExposureBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Exposure.ExposureBuilder builder) {
			ofNullable(getTradePortfolio()).ifPresent(builder::setTradePortfolio);
			ofNullable(getAggregateValue()).ifPresent(builder::setAggregateValue);
			ofNullable(getCalculationDateTime()).ifPresent(builder::setCalculationDateTime);
			ofNullable(getValuationDateTime()).ifPresent(builder::setValuationDateTime);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Exposure _that = getType().cast(o);
		
			if (!Objects.equals(tradePortfolio, _that.getTradePortfolio())) return false;
			if (!Objects.equals(aggregateValue, _that.getAggregateValue())) return false;
			if (!Objects.equals(calculationDateTime, _that.getCalculationDateTime())) return false;
			if (!Objects.equals(valuationDateTime, _that.getValuationDateTime())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (tradePortfolio != null ? tradePortfolio.hashCode() : 0);
			_result = 31 * _result + (aggregateValue != null ? aggregateValue.hashCode() : 0);
			_result = 31 * _result + (calculationDateTime != null ? calculationDateTime.hashCode() : 0);
			_result = 31 * _result + (valuationDateTime != null ? valuationDateTime.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Exposure {" +
				"tradePortfolio=" + this.tradePortfolio + ", " +
				"aggregateValue=" + this.aggregateValue + ", " +
				"calculationDateTime=" + this.calculationDateTime + ", " +
				"valuationDateTime=" + this.valuationDateTime +
			'}';
		}
	}

	/*********************** Builder Implementation of Exposure  ***********************/
	class ExposureBuilderImpl implements Exposure.ExposureBuilder {
	
		protected ReferenceWithMetaPortfolioState.ReferenceWithMetaPortfolioStateBuilder tradePortfolio;
		protected Money.MoneyBuilder aggregateValue;
		protected ZonedDateTime calculationDateTime;
		protected ZonedDateTime valuationDateTime;
	
		public ExposureBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("tradePortfolio")
		public ReferenceWithMetaPortfolioState.ReferenceWithMetaPortfolioStateBuilder getTradePortfolio() {
			return tradePortfolio;
		}
		
		@Override
		public ReferenceWithMetaPortfolioState.ReferenceWithMetaPortfolioStateBuilder getOrCreateTradePortfolio() {
			ReferenceWithMetaPortfolioState.ReferenceWithMetaPortfolioStateBuilder result;
			if (tradePortfolio!=null) {
				result = tradePortfolio;
			}
			else {
				result = tradePortfolio = ReferenceWithMetaPortfolioState.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("aggregateValue")
		public Money.MoneyBuilder getAggregateValue() {
			return aggregateValue;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateAggregateValue() {
			Money.MoneyBuilder result;
			if (aggregateValue!=null) {
				result = aggregateValue;
			}
			else {
				result = aggregateValue = Money.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("calculationDateTime")
		public ZonedDateTime getCalculationDateTime() {
			return calculationDateTime;
		}
		
		@Override
		@RosettaAttribute("valuationDateTime")
		public ZonedDateTime getValuationDateTime() {
			return valuationDateTime;
		}
		
	
		@Override
		@RosettaAttribute("tradePortfolio")
		public Exposure.ExposureBuilder setTradePortfolio(ReferenceWithMetaPortfolioState tradePortfolio) {
			this.tradePortfolio = tradePortfolio==null?null:tradePortfolio.toBuilder();
			return this;
		}
		@Override
		public Exposure.ExposureBuilder setTradePortfolioValue(PortfolioState tradePortfolio) {
			this.getOrCreateTradePortfolio().setValue(tradePortfolio);
			return this;
		}
		@Override
		@RosettaAttribute("aggregateValue")
		public Exposure.ExposureBuilder setAggregateValue(Money aggregateValue) {
			this.aggregateValue = aggregateValue==null?null:aggregateValue.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("calculationDateTime")
		public Exposure.ExposureBuilder setCalculationDateTime(ZonedDateTime calculationDateTime) {
			this.calculationDateTime = calculationDateTime==null?null:calculationDateTime;
			return this;
		}
		@Override
		@RosettaAttribute("valuationDateTime")
		public Exposure.ExposureBuilder setValuationDateTime(ZonedDateTime valuationDateTime) {
			this.valuationDateTime = valuationDateTime==null?null:valuationDateTime;
			return this;
		}
		
		@Override
		public Exposure build() {
			return new Exposure.ExposureImpl(this);
		}
		
		@Override
		public Exposure.ExposureBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Exposure.ExposureBuilder prune() {
			if (tradePortfolio!=null && !tradePortfolio.prune().hasData()) tradePortfolio = null;
			if (aggregateValue!=null && !aggregateValue.prune().hasData()) aggregateValue = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getTradePortfolio()!=null && getTradePortfolio().hasData()) return true;
			if (getAggregateValue()!=null && getAggregateValue().hasData()) return true;
			if (getCalculationDateTime()!=null) return true;
			if (getValuationDateTime()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Exposure.ExposureBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Exposure.ExposureBuilder o = (Exposure.ExposureBuilder) other;
			
			merger.mergeRosetta(getTradePortfolio(), o.getTradePortfolio(), this::setTradePortfolio);
			merger.mergeRosetta(getAggregateValue(), o.getAggregateValue(), this::setAggregateValue);
			
			merger.mergeBasic(getCalculationDateTime(), o.getCalculationDateTime(), this::setCalculationDateTime);
			merger.mergeBasic(getValuationDateTime(), o.getValuationDateTime(), this::setValuationDateTime);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Exposure _that = getType().cast(o);
		
			if (!Objects.equals(tradePortfolio, _that.getTradePortfolio())) return false;
			if (!Objects.equals(aggregateValue, _that.getAggregateValue())) return false;
			if (!Objects.equals(calculationDateTime, _that.getCalculationDateTime())) return false;
			if (!Objects.equals(valuationDateTime, _that.getValuationDateTime())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (tradePortfolio != null ? tradePortfolio.hashCode() : 0);
			_result = 31 * _result + (aggregateValue != null ? aggregateValue.hashCode() : 0);
			_result = 31 * _result + (calculationDateTime != null ? calculationDateTime.hashCode() : 0);
			_result = 31 * _result + (valuationDateTime != null ? valuationDateTime.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ExposureBuilder {" +
				"tradePortfolio=" + this.tradePortfolio + ", " +
				"aggregateValue=" + this.aggregateValue + ", " +
				"calculationDateTime=" + this.calculationDateTime + ", " +
				"valuationDateTime=" + this.valuationDateTime +
			'}';
		}
	}
}
