package cdm.observable.asset;

import cdm.base.math.ArithmeticOperationEnum;
import cdm.base.math.DatedValue;
import cdm.base.math.UnitType;
import cdm.observable.asset.CashPrice;
import cdm.observable.asset.Price;
import cdm.observable.asset.Price.PriceBuilder;
import cdm.observable.asset.Price.PriceBuilderImpl;
import cdm.observable.asset.Price.PriceImpl;
import cdm.observable.asset.PriceComposite;
import cdm.observable.asset.PriceExpressionEnum;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.PriceSchedule.PriceScheduleBuilder;
import cdm.observable.asset.PriceSchedule.PriceScheduleBuilderImpl;
import cdm.observable.asset.PriceSchedule.PriceScheduleImpl;
import cdm.observable.asset.PriceTypeEnum;
import cdm.observable.asset.meta.PriceMeta;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Specifies a price as a single value to be associated to a financial product. This data type extends PriceSchedule and requires that only the amount value exists.
 * @version ${project.version}
 */
@RosettaDataType(value="Price", builder=Price.PriceBuilderImpl.class, version="${project.version}")
public interface Price extends PriceSchedule {

	PriceMeta metaData = new PriceMeta();

	/*********************** Getter Methods  ***********************/

	/*********************** Build Methods  ***********************/
	Price build();
	
	Price.PriceBuilder toBuilder();
	
	static Price.PriceBuilder builder() {
		return new Price.PriceBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Price> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Price> getType() {
		return Price.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("value"), BigDecimal.class, getValue(), this);
		processRosetta(path.newSubPath("unit"), processor, UnitType.class, getUnit());
		processRosetta(path.newSubPath("datedValue"), processor, DatedValue.class, getDatedValue());
		processRosetta(path.newSubPath("perUnitOf"), processor, UnitType.class, getPerUnitOf());
		processor.processBasic(path.newSubPath("priceType"), PriceTypeEnum.class, getPriceType(), this);
		processor.processBasic(path.newSubPath("priceExpression"), PriceExpressionEnum.class, getPriceExpression(), this);
		processRosetta(path.newSubPath("composite"), processor, PriceComposite.class, getComposite());
		processor.processBasic(path.newSubPath("arithmeticOperator"), ArithmeticOperationEnum.class, getArithmeticOperator(), this);
		processRosetta(path.newSubPath("cashPrice"), processor, CashPrice.class, getCashPrice());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PriceBuilder extends Price, PriceSchedule.PriceScheduleBuilder, RosettaModelObjectBuilder {
		Price.PriceBuilder setValue(BigDecimal value);
		Price.PriceBuilder setUnit(UnitType unit);
		Price.PriceBuilder addDatedValue(DatedValue datedValue0);
		Price.PriceBuilder addDatedValue(DatedValue datedValue1, int _idx);
		Price.PriceBuilder addDatedValue(List<? extends DatedValue> datedValue2);
		Price.PriceBuilder setDatedValue(List<? extends DatedValue> datedValue3);
		Price.PriceBuilder setPerUnitOf(UnitType perUnitOf);
		Price.PriceBuilder setPriceType(PriceTypeEnum priceType);
		Price.PriceBuilder setPriceExpression(PriceExpressionEnum priceExpression);
		Price.PriceBuilder setComposite(PriceComposite composite);
		Price.PriceBuilder setArithmeticOperator(ArithmeticOperationEnum arithmeticOperator);
		Price.PriceBuilder setCashPrice(CashPrice cashPrice);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("value"), BigDecimal.class, getValue(), this);
			processRosetta(path.newSubPath("unit"), processor, UnitType.UnitTypeBuilder.class, getUnit());
			processRosetta(path.newSubPath("datedValue"), processor, DatedValue.DatedValueBuilder.class, getDatedValue());
			processRosetta(path.newSubPath("perUnitOf"), processor, UnitType.UnitTypeBuilder.class, getPerUnitOf());
			processor.processBasic(path.newSubPath("priceType"), PriceTypeEnum.class, getPriceType(), this);
			processor.processBasic(path.newSubPath("priceExpression"), PriceExpressionEnum.class, getPriceExpression(), this);
			processRosetta(path.newSubPath("composite"), processor, PriceComposite.PriceCompositeBuilder.class, getComposite());
			processor.processBasic(path.newSubPath("arithmeticOperator"), ArithmeticOperationEnum.class, getArithmeticOperator(), this);
			processRosetta(path.newSubPath("cashPrice"), processor, CashPrice.CashPriceBuilder.class, getCashPrice());
		}
		

		Price.PriceBuilder prune();
	}

	/*********************** Immutable Implementation of Price  ***********************/
	class PriceImpl extends PriceSchedule.PriceScheduleImpl implements Price {
		
		protected PriceImpl(Price.PriceBuilder builder) {
			super(builder);
		}
		
		@Override
		public Price build() {
			return this;
		}
		
		@Override
		public Price.PriceBuilder toBuilder() {
			Price.PriceBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Price.PriceBuilder builder) {
			super.setBuilderFields(builder);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
		
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			return _result;
		}
		
		@Override
		public String toString() {
			return "Price {" +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of Price  ***********************/
	class PriceBuilderImpl extends PriceSchedule.PriceScheduleBuilderImpl  implements Price.PriceBuilder {
	
	
		public PriceBuilderImpl() {
		}
	
	
		@Override
		@RosettaAttribute("value")
		public Price.PriceBuilder setValue(BigDecimal value) {
			this.value = value==null?null:value;
			return this;
		}
		@Override
		@RosettaAttribute("unit")
		public Price.PriceBuilder setUnit(UnitType unit) {
			this.unit = unit==null?null:unit.toBuilder();
			return this;
		}
		@Override
		public Price.PriceBuilder addDatedValue(DatedValue datedValue) {
			if (datedValue!=null) this.datedValue.add(datedValue.toBuilder());
			return this;
		}
		
		@Override
		public Price.PriceBuilder addDatedValue(DatedValue datedValue, int _idx) {
			getIndex(this.datedValue, _idx, () -> datedValue.toBuilder());
			return this;
		}
		@Override 
		public Price.PriceBuilder addDatedValue(List<? extends DatedValue> datedValues) {
			if (datedValues != null) {
				for (DatedValue toAdd : datedValues) {
					this.datedValue.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("datedValue")
		public Price.PriceBuilder setDatedValue(List<? extends DatedValue> datedValues) {
			if (datedValues == null)  {
				this.datedValue = new ArrayList<>();
			}
			else {
				this.datedValue = datedValues.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("perUnitOf")
		public Price.PriceBuilder setPerUnitOf(UnitType perUnitOf) {
			this.perUnitOf = perUnitOf==null?null:perUnitOf.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("priceType")
		public Price.PriceBuilder setPriceType(PriceTypeEnum priceType) {
			this.priceType = priceType==null?null:priceType;
			return this;
		}
		@Override
		@RosettaAttribute("priceExpression")
		public Price.PriceBuilder setPriceExpression(PriceExpressionEnum priceExpression) {
			this.priceExpression = priceExpression==null?null:priceExpression;
			return this;
		}
		@Override
		@RosettaAttribute("composite")
		public Price.PriceBuilder setComposite(PriceComposite composite) {
			this.composite = composite==null?null:composite.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("arithmeticOperator")
		public Price.PriceBuilder setArithmeticOperator(ArithmeticOperationEnum arithmeticOperator) {
			this.arithmeticOperator = arithmeticOperator==null?null:arithmeticOperator;
			return this;
		}
		@Override
		@RosettaAttribute("cashPrice")
		public Price.PriceBuilder setCashPrice(CashPrice cashPrice) {
			this.cashPrice = cashPrice==null?null:cashPrice.toBuilder();
			return this;
		}
		
		@Override
		public Price build() {
			return new Price.PriceImpl(this);
		}
		
		@Override
		public Price.PriceBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Price.PriceBuilder prune() {
			super.prune();
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Price.PriceBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			Price.PriceBuilder o = (Price.PriceBuilder) other;
			
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
		
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			return _result;
		}
		
		@Override
		public String toString() {
			return "PriceBuilder {" +
			'}' + " " + super.toString();
		}
	}
}
