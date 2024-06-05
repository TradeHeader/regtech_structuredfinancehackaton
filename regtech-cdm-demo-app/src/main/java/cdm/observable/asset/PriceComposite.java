package cdm.observable.asset;

import cdm.base.math.ArithmeticOperationEnum;
import cdm.observable.asset.PriceComposite;
import cdm.observable.asset.PriceComposite.PriceCompositeBuilder;
import cdm.observable.asset.PriceComposite.PriceCompositeBuilderImpl;
import cdm.observable.asset.PriceComposite.PriceCompositeImpl;
import cdm.observable.asset.PriceOperandEnum;
import cdm.observable.asset.meta.PriceCompositeMeta;
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
 * Defines the inputs required to calculate a price as a simple composite of 2 other values. The inputs consist of 2 numbers and a simple arithmetic operator. This generic data type applies to a variety of use cases where a price is obtained by simple composition, e.g. dirty = clean + accrued (Bond), forward rate = spot rate + forward point (FX) etc.
 * @version ${project.version}
 */
@RosettaDataType(value="PriceComposite", builder=PriceComposite.PriceCompositeBuilderImpl.class, version="${project.version}")
public interface PriceComposite extends RosettaModelObject {

	PriceCompositeMeta metaData = new PriceCompositeMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The 1st value in the arithmetic operation, which may be non-commutative in some cases: Subtract, Divide). This 1st operand is called &#39;baseValue&#39; as it refers to the price anchor in the arithmetic operation: e.g. the clean price (Bond) or the spot rate (FX).
	 */
	BigDecimal getBaseValue();
	/**
	 * The 2nd value in the arithmetic operation, which may be non-commutative in some cases: Subtract, Divide). The 2nd operand is called &#39;operand&#39; to distinguish it from the 1st one which is the price anchor.
	 */
	BigDecimal getOperand();
	/**
	 * Specifies the arithmetic operator via an enumeration.
	 */
	ArithmeticOperationEnum getArithmeticOperator();
	/**
	 * Optionally qualifies the type of operand: e.g. accrued or forward point.
	 */
	PriceOperandEnum getOperandType();

	/*********************** Build Methods  ***********************/
	PriceComposite build();
	
	PriceComposite.PriceCompositeBuilder toBuilder();
	
	static PriceComposite.PriceCompositeBuilder builder() {
		return new PriceComposite.PriceCompositeBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PriceComposite> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PriceComposite> getType() {
		return PriceComposite.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("baseValue"), BigDecimal.class, getBaseValue(), this);
		processor.processBasic(path.newSubPath("operand"), BigDecimal.class, getOperand(), this);
		processor.processBasic(path.newSubPath("arithmeticOperator"), ArithmeticOperationEnum.class, getArithmeticOperator(), this);
		processor.processBasic(path.newSubPath("operandType"), PriceOperandEnum.class, getOperandType(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface PriceCompositeBuilder extends PriceComposite, RosettaModelObjectBuilder {
		PriceComposite.PriceCompositeBuilder setBaseValue(BigDecimal baseValue);
		PriceComposite.PriceCompositeBuilder setOperand(BigDecimal operand);
		PriceComposite.PriceCompositeBuilder setArithmeticOperator(ArithmeticOperationEnum arithmeticOperator);
		PriceComposite.PriceCompositeBuilder setOperandType(PriceOperandEnum operandType);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("baseValue"), BigDecimal.class, getBaseValue(), this);
			processor.processBasic(path.newSubPath("operand"), BigDecimal.class, getOperand(), this);
			processor.processBasic(path.newSubPath("arithmeticOperator"), ArithmeticOperationEnum.class, getArithmeticOperator(), this);
			processor.processBasic(path.newSubPath("operandType"), PriceOperandEnum.class, getOperandType(), this);
		}
		

		PriceComposite.PriceCompositeBuilder prune();
	}

	/*********************** Immutable Implementation of PriceComposite  ***********************/
	class PriceCompositeImpl implements PriceComposite {
		private final BigDecimal baseValue;
		private final BigDecimal operand;
		private final ArithmeticOperationEnum arithmeticOperator;
		private final PriceOperandEnum operandType;
		
		protected PriceCompositeImpl(PriceComposite.PriceCompositeBuilder builder) {
			this.baseValue = builder.getBaseValue();
			this.operand = builder.getOperand();
			this.arithmeticOperator = builder.getArithmeticOperator();
			this.operandType = builder.getOperandType();
		}
		
		@Override
		@RosettaAttribute("baseValue")
		public BigDecimal getBaseValue() {
			return baseValue;
		}
		
		@Override
		@RosettaAttribute("operand")
		public BigDecimal getOperand() {
			return operand;
		}
		
		@Override
		@RosettaAttribute("arithmeticOperator")
		public ArithmeticOperationEnum getArithmeticOperator() {
			return arithmeticOperator;
		}
		
		@Override
		@RosettaAttribute("operandType")
		public PriceOperandEnum getOperandType() {
			return operandType;
		}
		
		@Override
		public PriceComposite build() {
			return this;
		}
		
		@Override
		public PriceComposite.PriceCompositeBuilder toBuilder() {
			PriceComposite.PriceCompositeBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PriceComposite.PriceCompositeBuilder builder) {
			ofNullable(getBaseValue()).ifPresent(builder::setBaseValue);
			ofNullable(getOperand()).ifPresent(builder::setOperand);
			ofNullable(getArithmeticOperator()).ifPresent(builder::setArithmeticOperator);
			ofNullable(getOperandType()).ifPresent(builder::setOperandType);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PriceComposite _that = getType().cast(o);
		
			if (!Objects.equals(baseValue, _that.getBaseValue())) return false;
			if (!Objects.equals(operand, _that.getOperand())) return false;
			if (!Objects.equals(arithmeticOperator, _that.getArithmeticOperator())) return false;
			if (!Objects.equals(operandType, _that.getOperandType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (baseValue != null ? baseValue.hashCode() : 0);
			_result = 31 * _result + (operand != null ? operand.hashCode() : 0);
			_result = 31 * _result + (arithmeticOperator != null ? arithmeticOperator.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (operandType != null ? operandType.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PriceComposite {" +
				"baseValue=" + this.baseValue + ", " +
				"operand=" + this.operand + ", " +
				"arithmeticOperator=" + this.arithmeticOperator + ", " +
				"operandType=" + this.operandType +
			'}';
		}
	}

	/*********************** Builder Implementation of PriceComposite  ***********************/
	class PriceCompositeBuilderImpl implements PriceComposite.PriceCompositeBuilder {
	
		protected BigDecimal baseValue;
		protected BigDecimal operand;
		protected ArithmeticOperationEnum arithmeticOperator;
		protected PriceOperandEnum operandType;
	
		public PriceCompositeBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("baseValue")
		public BigDecimal getBaseValue() {
			return baseValue;
		}
		
		@Override
		@RosettaAttribute("operand")
		public BigDecimal getOperand() {
			return operand;
		}
		
		@Override
		@RosettaAttribute("arithmeticOperator")
		public ArithmeticOperationEnum getArithmeticOperator() {
			return arithmeticOperator;
		}
		
		@Override
		@RosettaAttribute("operandType")
		public PriceOperandEnum getOperandType() {
			return operandType;
		}
		
	
		@Override
		@RosettaAttribute("baseValue")
		public PriceComposite.PriceCompositeBuilder setBaseValue(BigDecimal baseValue) {
			this.baseValue = baseValue==null?null:baseValue;
			return this;
		}
		@Override
		@RosettaAttribute("operand")
		public PriceComposite.PriceCompositeBuilder setOperand(BigDecimal operand) {
			this.operand = operand==null?null:operand;
			return this;
		}
		@Override
		@RosettaAttribute("arithmeticOperator")
		public PriceComposite.PriceCompositeBuilder setArithmeticOperator(ArithmeticOperationEnum arithmeticOperator) {
			this.arithmeticOperator = arithmeticOperator==null?null:arithmeticOperator;
			return this;
		}
		@Override
		@RosettaAttribute("operandType")
		public PriceComposite.PriceCompositeBuilder setOperandType(PriceOperandEnum operandType) {
			this.operandType = operandType==null?null:operandType;
			return this;
		}
		
		@Override
		public PriceComposite build() {
			return new PriceComposite.PriceCompositeImpl(this);
		}
		
		@Override
		public PriceComposite.PriceCompositeBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PriceComposite.PriceCompositeBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getBaseValue()!=null) return true;
			if (getOperand()!=null) return true;
			if (getArithmeticOperator()!=null) return true;
			if (getOperandType()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PriceComposite.PriceCompositeBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PriceComposite.PriceCompositeBuilder o = (PriceComposite.PriceCompositeBuilder) other;
			
			
			merger.mergeBasic(getBaseValue(), o.getBaseValue(), this::setBaseValue);
			merger.mergeBasic(getOperand(), o.getOperand(), this::setOperand);
			merger.mergeBasic(getArithmeticOperator(), o.getArithmeticOperator(), this::setArithmeticOperator);
			merger.mergeBasic(getOperandType(), o.getOperandType(), this::setOperandType);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PriceComposite _that = getType().cast(o);
		
			if (!Objects.equals(baseValue, _that.getBaseValue())) return false;
			if (!Objects.equals(operand, _that.getOperand())) return false;
			if (!Objects.equals(arithmeticOperator, _that.getArithmeticOperator())) return false;
			if (!Objects.equals(operandType, _that.getOperandType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (baseValue != null ? baseValue.hashCode() : 0);
			_result = 31 * _result + (operand != null ? operand.hashCode() : 0);
			_result = 31 * _result + (arithmeticOperator != null ? arithmeticOperator.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (operandType != null ? operandType.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PriceCompositeBuilder {" +
				"baseValue=" + this.baseValue + ", " +
				"operand=" + this.operand + ", " +
				"arithmeticOperator=" + this.arithmeticOperator + ", " +
				"operandType=" + this.operandType +
			'}';
		}
	}
}
