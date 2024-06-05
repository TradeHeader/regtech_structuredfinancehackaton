package cdm.product.asset;

import cdm.observable.asset.Money;
import cdm.product.asset.StubFloatingRate;
import cdm.product.asset.StubValue;
import cdm.product.asset.StubValue.StubValueBuilder;
import cdm.product.asset.StubValue.StubValueBuilderImpl;
import cdm.product.asset.StubValue.StubValueImpl;
import cdm.product.asset.meta.StubValueMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.util.ListEquals;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A type defining how a stub calculation period amount is calculated. A single floating rate tenor different to that used for the regular part of the calculation periods schedule may be specified, or two floating rate tenors many be specified. If two floating rate tenors are specified then Linear Interpolation (in accordance with the 2000 ISDA Definitions, Section 8.3 Interpolation) is assumed to apply. Alternatively, an actual known stub rate or stub amount may be specified.
 * @version ${project.version}
 */
@RosettaDataType(value="StubValue", builder=StubValue.StubValueBuilderImpl.class, version="${project.version}")
public interface StubValue extends RosettaModelObject {

	StubValueMeta metaData = new StubValueMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The rates to be applied to the initial or final stub may be the linear interpolation of two different rates. While the majority of the time, the rate indices will be the same as that specified in the stream and only the tenor itself will be different, it is possible to specift two different rates. For example, a 2 month stub period may use the linear interpolation of a 1 month and 3 month rate. The different rates would be specified in this component. Note that a maximum of two rates can be specified. If a stub period uses the same floating rate index, including tenor, as the regular calculation periods then this should not be specified again within this component, i.e. the stub calculation period amount component may not need to be specified even if there is an initial or final stub period. If a stub period uses a different floating rate index compared to the regular calculation periods then this should be specified within this component. If specified here, they are likely to have id attributes, allowing them to be referenced from within the cashflows component.
	 */
	List<? extends StubFloatingRate> getFloatingRate();
	/**
	 * An actual rate to apply for the initial or final stub period may have been agreed between the principal parties (in a similar way to how an initial rate may have been agreed for the first regular period). If an actual stub rate has been agreed then it would be included in this component. It will be a per annum rate, expressed as a decimal. A stub rate of 5% would be represented as 0.05.
	 */
	BigDecimal getStubRate();
	/**
	 * An actual amount to apply for the initial or final stub period may have been agreed between the two parties. If an actual stub amount has been agreed then it would be included in this component.
	 */
	Money getStubAmount();

	/*********************** Build Methods  ***********************/
	StubValue build();
	
	StubValue.StubValueBuilder toBuilder();
	
	static StubValue.StubValueBuilder builder() {
		return new StubValue.StubValueBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends StubValue> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends StubValue> getType() {
		return StubValue.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("floatingRate"), processor, StubFloatingRate.class, getFloatingRate());
		processor.processBasic(path.newSubPath("stubRate"), BigDecimal.class, getStubRate(), this);
		processRosetta(path.newSubPath("stubAmount"), processor, Money.class, getStubAmount());
	}
	

	/*********************** Builder Interface  ***********************/
	interface StubValueBuilder extends StubValue, RosettaModelObjectBuilder {
		StubFloatingRate.StubFloatingRateBuilder getOrCreateFloatingRate(int _index);
		List<? extends StubFloatingRate.StubFloatingRateBuilder> getFloatingRate();
		Money.MoneyBuilder getOrCreateStubAmount();
		Money.MoneyBuilder getStubAmount();
		StubValue.StubValueBuilder addFloatingRate(StubFloatingRate floatingRate0);
		StubValue.StubValueBuilder addFloatingRate(StubFloatingRate floatingRate1, int _idx);
		StubValue.StubValueBuilder addFloatingRate(List<? extends StubFloatingRate> floatingRate2);
		StubValue.StubValueBuilder setFloatingRate(List<? extends StubFloatingRate> floatingRate3);
		StubValue.StubValueBuilder setStubRate(BigDecimal stubRate);
		StubValue.StubValueBuilder setStubAmount(Money stubAmount);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("floatingRate"), processor, StubFloatingRate.StubFloatingRateBuilder.class, getFloatingRate());
			processor.processBasic(path.newSubPath("stubRate"), BigDecimal.class, getStubRate(), this);
			processRosetta(path.newSubPath("stubAmount"), processor, Money.MoneyBuilder.class, getStubAmount());
		}
		

		StubValue.StubValueBuilder prune();
	}

	/*********************** Immutable Implementation of StubValue  ***********************/
	class StubValueImpl implements StubValue {
		private final List<? extends StubFloatingRate> floatingRate;
		private final BigDecimal stubRate;
		private final Money stubAmount;
		
		protected StubValueImpl(StubValue.StubValueBuilder builder) {
			this.floatingRate = ofNullable(builder.getFloatingRate()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.stubRate = builder.getStubRate();
			this.stubAmount = ofNullable(builder.getStubAmount()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("floatingRate")
		public List<? extends StubFloatingRate> getFloatingRate() {
			return floatingRate;
		}
		
		@Override
		@RosettaAttribute("stubRate")
		public BigDecimal getStubRate() {
			return stubRate;
		}
		
		@Override
		@RosettaAttribute("stubAmount")
		public Money getStubAmount() {
			return stubAmount;
		}
		
		@Override
		public StubValue build() {
			return this;
		}
		
		@Override
		public StubValue.StubValueBuilder toBuilder() {
			StubValue.StubValueBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(StubValue.StubValueBuilder builder) {
			ofNullable(getFloatingRate()).ifPresent(builder::setFloatingRate);
			ofNullable(getStubRate()).ifPresent(builder::setStubRate);
			ofNullable(getStubAmount()).ifPresent(builder::setStubAmount);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			StubValue _that = getType().cast(o);
		
			if (!ListEquals.listEquals(floatingRate, _that.getFloatingRate())) return false;
			if (!Objects.equals(stubRate, _that.getStubRate())) return false;
			if (!Objects.equals(stubAmount, _that.getStubAmount())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (floatingRate != null ? floatingRate.hashCode() : 0);
			_result = 31 * _result + (stubRate != null ? stubRate.hashCode() : 0);
			_result = 31 * _result + (stubAmount != null ? stubAmount.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "StubValue {" +
				"floatingRate=" + this.floatingRate + ", " +
				"stubRate=" + this.stubRate + ", " +
				"stubAmount=" + this.stubAmount +
			'}';
		}
	}

	/*********************** Builder Implementation of StubValue  ***********************/
	class StubValueBuilderImpl implements StubValue.StubValueBuilder {
	
		protected List<StubFloatingRate.StubFloatingRateBuilder> floatingRate = new ArrayList<>();
		protected BigDecimal stubRate;
		protected Money.MoneyBuilder stubAmount;
	
		public StubValueBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("floatingRate")
		public List<? extends StubFloatingRate.StubFloatingRateBuilder> getFloatingRate() {
			return floatingRate;
		}
		
		public StubFloatingRate.StubFloatingRateBuilder getOrCreateFloatingRate(int _index) {
		
			if (floatingRate==null) {
				this.floatingRate = new ArrayList<>();
			}
			StubFloatingRate.StubFloatingRateBuilder result;
			return getIndex(floatingRate, _index, () -> {
						StubFloatingRate.StubFloatingRateBuilder newFloatingRate = StubFloatingRate.builder();
						return newFloatingRate;
					});
		}
		
		@Override
		@RosettaAttribute("stubRate")
		public BigDecimal getStubRate() {
			return stubRate;
		}
		
		@Override
		@RosettaAttribute("stubAmount")
		public Money.MoneyBuilder getStubAmount() {
			return stubAmount;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateStubAmount() {
			Money.MoneyBuilder result;
			if (stubAmount!=null) {
				result = stubAmount;
			}
			else {
				result = stubAmount = Money.builder();
			}
			
			return result;
		}
	
		@Override
		public StubValue.StubValueBuilder addFloatingRate(StubFloatingRate floatingRate) {
			if (floatingRate!=null) this.floatingRate.add(floatingRate.toBuilder());
			return this;
		}
		
		@Override
		public StubValue.StubValueBuilder addFloatingRate(StubFloatingRate floatingRate, int _idx) {
			getIndex(this.floatingRate, _idx, () -> floatingRate.toBuilder());
			return this;
		}
		@Override 
		public StubValue.StubValueBuilder addFloatingRate(List<? extends StubFloatingRate> floatingRates) {
			if (floatingRates != null) {
				for (StubFloatingRate toAdd : floatingRates) {
					this.floatingRate.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("floatingRate")
		public StubValue.StubValueBuilder setFloatingRate(List<? extends StubFloatingRate> floatingRates) {
			if (floatingRates == null)  {
				this.floatingRate = new ArrayList<>();
			}
			else {
				this.floatingRate = floatingRates.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("stubRate")
		public StubValue.StubValueBuilder setStubRate(BigDecimal stubRate) {
			this.stubRate = stubRate==null?null:stubRate;
			return this;
		}
		@Override
		@RosettaAttribute("stubAmount")
		public StubValue.StubValueBuilder setStubAmount(Money stubAmount) {
			this.stubAmount = stubAmount==null?null:stubAmount.toBuilder();
			return this;
		}
		
		@Override
		public StubValue build() {
			return new StubValue.StubValueImpl(this);
		}
		
		@Override
		public StubValue.StubValueBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public StubValue.StubValueBuilder prune() {
			floatingRate = floatingRate.stream().filter(b->b!=null).<StubFloatingRate.StubFloatingRateBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (stubAmount!=null && !stubAmount.prune().hasData()) stubAmount = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getFloatingRate()!=null && getFloatingRate().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getStubRate()!=null) return true;
			if (getStubAmount()!=null && getStubAmount().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public StubValue.StubValueBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			StubValue.StubValueBuilder o = (StubValue.StubValueBuilder) other;
			
			merger.mergeRosetta(getFloatingRate(), o.getFloatingRate(), this::getOrCreateFloatingRate);
			merger.mergeRosetta(getStubAmount(), o.getStubAmount(), this::setStubAmount);
			
			merger.mergeBasic(getStubRate(), o.getStubRate(), this::setStubRate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			StubValue _that = getType().cast(o);
		
			if (!ListEquals.listEquals(floatingRate, _that.getFloatingRate())) return false;
			if (!Objects.equals(stubRate, _that.getStubRate())) return false;
			if (!Objects.equals(stubAmount, _that.getStubAmount())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (floatingRate != null ? floatingRate.hashCode() : 0);
			_result = 31 * _result + (stubRate != null ? stubRate.hashCode() : 0);
			_result = 31 * _result + (stubAmount != null ? stubAmount.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "StubValueBuilder {" +
				"floatingRate=" + this.floatingRate + ", " +
				"stubRate=" + this.stubRate + ", " +
				"stubAmount=" + this.stubAmount +
			'}';
		}
	}
}
