package cdm.product.common.settlement;

import cdm.product.common.settlement.ComputedAmount;
import cdm.product.common.settlement.ComputedAmount.ComputedAmountBuilder;
import cdm.product.common.settlement.ComputedAmount.ComputedAmountBuilderImpl;
import cdm.product.common.settlement.ComputedAmount.ComputedAmountImpl;
import cdm.product.common.settlement.meta.ComputedAmountMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class to specify the outcome of a computed amount, for testing purposes.
 * @version ${project.version}
 */
@RosettaDataType(value="ComputedAmount", builder=ComputedAmount.ComputedAmountBuilderImpl.class, version="${project.version}")
public interface ComputedAmount extends RosettaModelObject {

	ComputedAmountMeta metaData = new ComputedAmountMeta();

	/*********************** Getter Methods  ***********************/
	String getCallFunction();
	BigDecimal getAmount();
	/**
	 * The currency in which the computed amount is denominated. The list of valid currencies is not presently positioned as an enumeration as part of the CDM because that scope is limited to the values specified by ISDA and FpML. As a result, implementers have to make reference to the relevant standard, such as the ISO 4217 standard for currency codes.
	 */
	FieldWithMetaString getCurrency();

	/*********************** Build Methods  ***********************/
	ComputedAmount build();
	
	ComputedAmount.ComputedAmountBuilder toBuilder();
	
	static ComputedAmount.ComputedAmountBuilder builder() {
		return new ComputedAmount.ComputedAmountBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ComputedAmount> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ComputedAmount> getType() {
		return ComputedAmount.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("callFunction"), String.class, getCallFunction(), this);
		processor.processBasic(path.newSubPath("amount"), BigDecimal.class, getAmount(), this);
		processRosetta(path.newSubPath("currency"), processor, FieldWithMetaString.class, getCurrency());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ComputedAmountBuilder extends ComputedAmount, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateCurrency();
		FieldWithMetaString.FieldWithMetaStringBuilder getCurrency();
		ComputedAmount.ComputedAmountBuilder setCallFunction(String callFunction);
		ComputedAmount.ComputedAmountBuilder setAmount(BigDecimal amount);
		ComputedAmount.ComputedAmountBuilder setCurrency(FieldWithMetaString currency0);
		ComputedAmount.ComputedAmountBuilder setCurrencyValue(String currency1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("callFunction"), String.class, getCallFunction(), this);
			processor.processBasic(path.newSubPath("amount"), BigDecimal.class, getAmount(), this);
			processRosetta(path.newSubPath("currency"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getCurrency());
		}
		

		ComputedAmount.ComputedAmountBuilder prune();
	}

	/*********************** Immutable Implementation of ComputedAmount  ***********************/
	class ComputedAmountImpl implements ComputedAmount {
		private final String callFunction;
		private final BigDecimal amount;
		private final FieldWithMetaString currency;
		
		protected ComputedAmountImpl(ComputedAmount.ComputedAmountBuilder builder) {
			this.callFunction = builder.getCallFunction();
			this.amount = builder.getAmount();
			this.currency = ofNullable(builder.getCurrency()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("callFunction")
		public String getCallFunction() {
			return callFunction;
		}
		
		@Override
		@RosettaAttribute("amount")
		public BigDecimal getAmount() {
			return amount;
		}
		
		@Override
		@RosettaAttribute("currency")
		public FieldWithMetaString getCurrency() {
			return currency;
		}
		
		@Override
		public ComputedAmount build() {
			return this;
		}
		
		@Override
		public ComputedAmount.ComputedAmountBuilder toBuilder() {
			ComputedAmount.ComputedAmountBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ComputedAmount.ComputedAmountBuilder builder) {
			ofNullable(getCallFunction()).ifPresent(builder::setCallFunction);
			ofNullable(getAmount()).ifPresent(builder::setAmount);
			ofNullable(getCurrency()).ifPresent(builder::setCurrency);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ComputedAmount _that = getType().cast(o);
		
			if (!Objects.equals(callFunction, _that.getCallFunction())) return false;
			if (!Objects.equals(amount, _that.getAmount())) return false;
			if (!Objects.equals(currency, _that.getCurrency())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (callFunction != null ? callFunction.hashCode() : 0);
			_result = 31 * _result + (amount != null ? amount.hashCode() : 0);
			_result = 31 * _result + (currency != null ? currency.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ComputedAmount {" +
				"callFunction=" + this.callFunction + ", " +
				"amount=" + this.amount + ", " +
				"currency=" + this.currency +
			'}';
		}
	}

	/*********************** Builder Implementation of ComputedAmount  ***********************/
	class ComputedAmountBuilderImpl implements ComputedAmount.ComputedAmountBuilder {
	
		protected String callFunction;
		protected BigDecimal amount;
		protected FieldWithMetaString.FieldWithMetaStringBuilder currency;
	
		public ComputedAmountBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("callFunction")
		public String getCallFunction() {
			return callFunction;
		}
		
		@Override
		@RosettaAttribute("amount")
		public BigDecimal getAmount() {
			return amount;
		}
		
		@Override
		@RosettaAttribute("currency")
		public FieldWithMetaString.FieldWithMetaStringBuilder getCurrency() {
			return currency;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateCurrency() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (currency!=null) {
				result = currency;
			}
			else {
				result = currency = FieldWithMetaString.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("callFunction")
		public ComputedAmount.ComputedAmountBuilder setCallFunction(String callFunction) {
			this.callFunction = callFunction==null?null:callFunction;
			return this;
		}
		@Override
		@RosettaAttribute("amount")
		public ComputedAmount.ComputedAmountBuilder setAmount(BigDecimal amount) {
			this.amount = amount==null?null:amount;
			return this;
		}
		@Override
		@RosettaAttribute("currency")
		public ComputedAmount.ComputedAmountBuilder setCurrency(FieldWithMetaString currency) {
			this.currency = currency==null?null:currency.toBuilder();
			return this;
		}
		@Override
		public ComputedAmount.ComputedAmountBuilder setCurrencyValue(String currency) {
			this.getOrCreateCurrency().setValue(currency);
			return this;
		}
		
		@Override
		public ComputedAmount build() {
			return new ComputedAmount.ComputedAmountImpl(this);
		}
		
		@Override
		public ComputedAmount.ComputedAmountBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ComputedAmount.ComputedAmountBuilder prune() {
			if (currency!=null && !currency.prune().hasData()) currency = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCallFunction()!=null) return true;
			if (getAmount()!=null) return true;
			if (getCurrency()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ComputedAmount.ComputedAmountBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ComputedAmount.ComputedAmountBuilder o = (ComputedAmount.ComputedAmountBuilder) other;
			
			merger.mergeRosetta(getCurrency(), o.getCurrency(), this::setCurrency);
			
			merger.mergeBasic(getCallFunction(), o.getCallFunction(), this::setCallFunction);
			merger.mergeBasic(getAmount(), o.getAmount(), this::setAmount);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ComputedAmount _that = getType().cast(o);
		
			if (!Objects.equals(callFunction, _that.getCallFunction())) return false;
			if (!Objects.equals(amount, _that.getAmount())) return false;
			if (!Objects.equals(currency, _that.getCurrency())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (callFunction != null ? callFunction.hashCode() : 0);
			_result = 31 * _result + (amount != null ? amount.hashCode() : 0);
			_result = 31 * _result + (currency != null ? currency.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ComputedAmountBuilder {" +
				"callFunction=" + this.callFunction + ", " +
				"amount=" + this.amount + ", " +
				"currency=" + this.currency +
			'}';
		}
	}
}
