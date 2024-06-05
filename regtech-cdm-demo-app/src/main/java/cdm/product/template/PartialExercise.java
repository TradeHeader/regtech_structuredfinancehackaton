package cdm.product.template;

import cdm.observable.asset.Money;
import cdm.observable.asset.metafields.ReferenceWithMetaMoney;
import cdm.observable.asset.metafields.ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder;
import cdm.product.template.PartialExercise;
import cdm.product.template.PartialExercise.PartialExerciseBuilder;
import cdm.product.template.PartialExercise.PartialExerciseBuilderImpl;
import cdm.product.template.PartialExercise.PartialExerciseImpl;
import cdm.product.template.meta.PartialExerciseMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class defining partial exercise. As defined in the 2000 ISDA Definitions, Section 12.3 Partial Exercise, the buyer of the option may exercise all or less than all the notional amount of the underlying swap but may not be less than the minimum notional amount (if specified) and must be an integral multiple of the integral multiple amount if specified.
 * @version ${project.version}
 */
@RosettaDataType(value="PartialExercise", builder=PartialExercise.PartialExerciseBuilderImpl.class, version="${project.version}")
public interface PartialExercise extends RosettaModelObject {

	PartialExerciseMeta metaData = new PartialExerciseMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A pointer style reference to the associated notional schedule defined elsewhere in the document. This element has been made optional as part of its integration in the OptionBaseExtended, because not required for the options on securities.
	 */
	ReferenceWithMetaMoney getNotionaReference();
	/**
	 * A notional amount which restricts the amount of notional that can be exercised when partial exercise or multiple exercise is applicable. The integral multiple amount defines a lower limit of notional that can be exercised and also defines a unit multiple of notional that can be exercised, i.e. only integer multiples of this amount can be exercised.
	 */
	BigDecimal getIntegralMultipleAmount();
	/**
	 * The minimum notional amount that can be exercised on a given exercise date. See multipleExercise.
	 */
	BigDecimal getMinimumNotionalAmount();
	/**
	 * The minimum number of options that can be exercised on a given exercise date.
	 */
	Integer getMinimumNumberOfOptions();

	/*********************** Build Methods  ***********************/
	PartialExercise build();
	
	PartialExercise.PartialExerciseBuilder toBuilder();
	
	static PartialExercise.PartialExerciseBuilder builder() {
		return new PartialExercise.PartialExerciseBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PartialExercise> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PartialExercise> getType() {
		return PartialExercise.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("notionaReference"), processor, ReferenceWithMetaMoney.class, getNotionaReference());
		processor.processBasic(path.newSubPath("integralMultipleAmount"), BigDecimal.class, getIntegralMultipleAmount(), this);
		processor.processBasic(path.newSubPath("minimumNotionalAmount"), BigDecimal.class, getMinimumNotionalAmount(), this);
		processor.processBasic(path.newSubPath("minimumNumberOfOptions"), Integer.class, getMinimumNumberOfOptions(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface PartialExerciseBuilder extends PartialExercise, RosettaModelObjectBuilder {
		ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder getOrCreateNotionaReference();
		ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder getNotionaReference();
		PartialExercise.PartialExerciseBuilder setNotionaReference(ReferenceWithMetaMoney notionaReference0);
		PartialExercise.PartialExerciseBuilder setNotionaReferenceValue(Money notionaReference1);
		PartialExercise.PartialExerciseBuilder setIntegralMultipleAmount(BigDecimal integralMultipleAmount);
		PartialExercise.PartialExerciseBuilder setMinimumNotionalAmount(BigDecimal minimumNotionalAmount);
		PartialExercise.PartialExerciseBuilder setMinimumNumberOfOptions(Integer minimumNumberOfOptions);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("notionaReference"), processor, ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder.class, getNotionaReference());
			processor.processBasic(path.newSubPath("integralMultipleAmount"), BigDecimal.class, getIntegralMultipleAmount(), this);
			processor.processBasic(path.newSubPath("minimumNotionalAmount"), BigDecimal.class, getMinimumNotionalAmount(), this);
			processor.processBasic(path.newSubPath("minimumNumberOfOptions"), Integer.class, getMinimumNumberOfOptions(), this);
		}
		

		PartialExercise.PartialExerciseBuilder prune();
	}

	/*********************** Immutable Implementation of PartialExercise  ***********************/
	class PartialExerciseImpl implements PartialExercise {
		private final ReferenceWithMetaMoney notionaReference;
		private final BigDecimal integralMultipleAmount;
		private final BigDecimal minimumNotionalAmount;
		private final Integer minimumNumberOfOptions;
		
		protected PartialExerciseImpl(PartialExercise.PartialExerciseBuilder builder) {
			this.notionaReference = ofNullable(builder.getNotionaReference()).map(f->f.build()).orElse(null);
			this.integralMultipleAmount = builder.getIntegralMultipleAmount();
			this.minimumNotionalAmount = builder.getMinimumNotionalAmount();
			this.minimumNumberOfOptions = builder.getMinimumNumberOfOptions();
		}
		
		@Override
		@RosettaAttribute("notionaReference")
		public ReferenceWithMetaMoney getNotionaReference() {
			return notionaReference;
		}
		
		@Override
		@RosettaAttribute("integralMultipleAmount")
		public BigDecimal getIntegralMultipleAmount() {
			return integralMultipleAmount;
		}
		
		@Override
		@RosettaAttribute("minimumNotionalAmount")
		public BigDecimal getMinimumNotionalAmount() {
			return minimumNotionalAmount;
		}
		
		@Override
		@RosettaAttribute("minimumNumberOfOptions")
		public Integer getMinimumNumberOfOptions() {
			return minimumNumberOfOptions;
		}
		
		@Override
		public PartialExercise build() {
			return this;
		}
		
		@Override
		public PartialExercise.PartialExerciseBuilder toBuilder() {
			PartialExercise.PartialExerciseBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PartialExercise.PartialExerciseBuilder builder) {
			ofNullable(getNotionaReference()).ifPresent(builder::setNotionaReference);
			ofNullable(getIntegralMultipleAmount()).ifPresent(builder::setIntegralMultipleAmount);
			ofNullable(getMinimumNotionalAmount()).ifPresent(builder::setMinimumNotionalAmount);
			ofNullable(getMinimumNumberOfOptions()).ifPresent(builder::setMinimumNumberOfOptions);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PartialExercise _that = getType().cast(o);
		
			if (!Objects.equals(notionaReference, _that.getNotionaReference())) return false;
			if (!Objects.equals(integralMultipleAmount, _that.getIntegralMultipleAmount())) return false;
			if (!Objects.equals(minimumNotionalAmount, _that.getMinimumNotionalAmount())) return false;
			if (!Objects.equals(minimumNumberOfOptions, _that.getMinimumNumberOfOptions())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (notionaReference != null ? notionaReference.hashCode() : 0);
			_result = 31 * _result + (integralMultipleAmount != null ? integralMultipleAmount.hashCode() : 0);
			_result = 31 * _result + (minimumNotionalAmount != null ? minimumNotionalAmount.hashCode() : 0);
			_result = 31 * _result + (minimumNumberOfOptions != null ? minimumNumberOfOptions.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PartialExercise {" +
				"notionaReference=" + this.notionaReference + ", " +
				"integralMultipleAmount=" + this.integralMultipleAmount + ", " +
				"minimumNotionalAmount=" + this.minimumNotionalAmount + ", " +
				"minimumNumberOfOptions=" + this.minimumNumberOfOptions +
			'}';
		}
	}

	/*********************** Builder Implementation of PartialExercise  ***********************/
	class PartialExerciseBuilderImpl implements PartialExercise.PartialExerciseBuilder {
	
		protected ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder notionaReference;
		protected BigDecimal integralMultipleAmount;
		protected BigDecimal minimumNotionalAmount;
		protected Integer minimumNumberOfOptions;
	
		public PartialExerciseBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("notionaReference")
		public ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder getNotionaReference() {
			return notionaReference;
		}
		
		@Override
		public ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder getOrCreateNotionaReference() {
			ReferenceWithMetaMoney.ReferenceWithMetaMoneyBuilder result;
			if (notionaReference!=null) {
				result = notionaReference;
			}
			else {
				result = notionaReference = ReferenceWithMetaMoney.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("integralMultipleAmount")
		public BigDecimal getIntegralMultipleAmount() {
			return integralMultipleAmount;
		}
		
		@Override
		@RosettaAttribute("minimumNotionalAmount")
		public BigDecimal getMinimumNotionalAmount() {
			return minimumNotionalAmount;
		}
		
		@Override
		@RosettaAttribute("minimumNumberOfOptions")
		public Integer getMinimumNumberOfOptions() {
			return minimumNumberOfOptions;
		}
		
	
		@Override
		@RosettaAttribute("notionaReference")
		public PartialExercise.PartialExerciseBuilder setNotionaReference(ReferenceWithMetaMoney notionaReference) {
			this.notionaReference = notionaReference==null?null:notionaReference.toBuilder();
			return this;
		}
		@Override
		public PartialExercise.PartialExerciseBuilder setNotionaReferenceValue(Money notionaReference) {
			this.getOrCreateNotionaReference().setValue(notionaReference);
			return this;
		}
		@Override
		@RosettaAttribute("integralMultipleAmount")
		public PartialExercise.PartialExerciseBuilder setIntegralMultipleAmount(BigDecimal integralMultipleAmount) {
			this.integralMultipleAmount = integralMultipleAmount==null?null:integralMultipleAmount;
			return this;
		}
		@Override
		@RosettaAttribute("minimumNotionalAmount")
		public PartialExercise.PartialExerciseBuilder setMinimumNotionalAmount(BigDecimal minimumNotionalAmount) {
			this.minimumNotionalAmount = minimumNotionalAmount==null?null:minimumNotionalAmount;
			return this;
		}
		@Override
		@RosettaAttribute("minimumNumberOfOptions")
		public PartialExercise.PartialExerciseBuilder setMinimumNumberOfOptions(Integer minimumNumberOfOptions) {
			this.minimumNumberOfOptions = minimumNumberOfOptions==null?null:minimumNumberOfOptions;
			return this;
		}
		
		@Override
		public PartialExercise build() {
			return new PartialExercise.PartialExerciseImpl(this);
		}
		
		@Override
		public PartialExercise.PartialExerciseBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PartialExercise.PartialExerciseBuilder prune() {
			if (notionaReference!=null && !notionaReference.prune().hasData()) notionaReference = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getNotionaReference()!=null && getNotionaReference().hasData()) return true;
			if (getIntegralMultipleAmount()!=null) return true;
			if (getMinimumNotionalAmount()!=null) return true;
			if (getMinimumNumberOfOptions()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PartialExercise.PartialExerciseBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PartialExercise.PartialExerciseBuilder o = (PartialExercise.PartialExerciseBuilder) other;
			
			merger.mergeRosetta(getNotionaReference(), o.getNotionaReference(), this::setNotionaReference);
			
			merger.mergeBasic(getIntegralMultipleAmount(), o.getIntegralMultipleAmount(), this::setIntegralMultipleAmount);
			merger.mergeBasic(getMinimumNotionalAmount(), o.getMinimumNotionalAmount(), this::setMinimumNotionalAmount);
			merger.mergeBasic(getMinimumNumberOfOptions(), o.getMinimumNumberOfOptions(), this::setMinimumNumberOfOptions);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PartialExercise _that = getType().cast(o);
		
			if (!Objects.equals(notionaReference, _that.getNotionaReference())) return false;
			if (!Objects.equals(integralMultipleAmount, _that.getIntegralMultipleAmount())) return false;
			if (!Objects.equals(minimumNotionalAmount, _that.getMinimumNotionalAmount())) return false;
			if (!Objects.equals(minimumNumberOfOptions, _that.getMinimumNumberOfOptions())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (notionaReference != null ? notionaReference.hashCode() : 0);
			_result = 31 * _result + (integralMultipleAmount != null ? integralMultipleAmount.hashCode() : 0);
			_result = 31 * _result + (minimumNotionalAmount != null ? minimumNotionalAmount.hashCode() : 0);
			_result = 31 * _result + (minimumNumberOfOptions != null ? minimumNumberOfOptions.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PartialExerciseBuilder {" +
				"notionaReference=" + this.notionaReference + ", " +
				"integralMultipleAmount=" + this.integralMultipleAmount + ", " +
				"minimumNotionalAmount=" + this.minimumNotionalAmount + ", " +
				"minimumNumberOfOptions=" + this.minimumNumberOfOptions +
			'}';
		}
	}
}
