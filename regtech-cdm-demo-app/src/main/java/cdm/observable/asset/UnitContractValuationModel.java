package cdm.observable.asset;

import cdm.base.math.Quantity;
import cdm.observable.asset.Money;
import cdm.observable.asset.UnitContractValuationModel;
import cdm.observable.asset.UnitContractValuationModel.UnitContractValuationModelBuilder;
import cdm.observable.asset.UnitContractValuationModel.UnitContractValuationModelBuilderImpl;
import cdm.observable.asset.UnitContractValuationModel.UnitContractValuationModelImpl;
import cdm.observable.asset.meta.UnitContractValuationModelMeta;
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
 *  Unit contract model for security valuation, e.g. for equity, modelled onto UnitContract.model in FpML.
 * @version ${project.version}
 */
@RosettaDataType(value="UnitContractValuationModel", builder=UnitContractValuationModel.UnitContractValuationModelBuilderImpl.class, version="${project.version}")
public interface UnitContractValuationModel extends RosettaModelObject {

	UnitContractValuationModelMeta metaData = new UnitContractValuationModelMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The number of units (index or securities).
	 */
	Quantity getNumberOfUnits();
	/**
	 * The price of each unit.
	 */
	Money getUnitPrice();

	/*********************** Build Methods  ***********************/
	UnitContractValuationModel build();
	
	UnitContractValuationModel.UnitContractValuationModelBuilder toBuilder();
	
	static UnitContractValuationModel.UnitContractValuationModelBuilder builder() {
		return new UnitContractValuationModel.UnitContractValuationModelBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends UnitContractValuationModel> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends UnitContractValuationModel> getType() {
		return UnitContractValuationModel.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("numberOfUnits"), processor, Quantity.class, getNumberOfUnits());
		processRosetta(path.newSubPath("unitPrice"), processor, Money.class, getUnitPrice());
	}
	

	/*********************** Builder Interface  ***********************/
	interface UnitContractValuationModelBuilder extends UnitContractValuationModel, RosettaModelObjectBuilder {
		Quantity.QuantityBuilder getOrCreateNumberOfUnits();
		Quantity.QuantityBuilder getNumberOfUnits();
		Money.MoneyBuilder getOrCreateUnitPrice();
		Money.MoneyBuilder getUnitPrice();
		UnitContractValuationModel.UnitContractValuationModelBuilder setNumberOfUnits(Quantity numberOfUnits);
		UnitContractValuationModel.UnitContractValuationModelBuilder setUnitPrice(Money unitPrice);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("numberOfUnits"), processor, Quantity.QuantityBuilder.class, getNumberOfUnits());
			processRosetta(path.newSubPath("unitPrice"), processor, Money.MoneyBuilder.class, getUnitPrice());
		}
		

		UnitContractValuationModel.UnitContractValuationModelBuilder prune();
	}

	/*********************** Immutable Implementation of UnitContractValuationModel  ***********************/
	class UnitContractValuationModelImpl implements UnitContractValuationModel {
		private final Quantity numberOfUnits;
		private final Money unitPrice;
		
		protected UnitContractValuationModelImpl(UnitContractValuationModel.UnitContractValuationModelBuilder builder) {
			this.numberOfUnits = ofNullable(builder.getNumberOfUnits()).map(f->f.build()).orElse(null);
			this.unitPrice = ofNullable(builder.getUnitPrice()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("numberOfUnits")
		public Quantity getNumberOfUnits() {
			return numberOfUnits;
		}
		
		@Override
		@RosettaAttribute("unitPrice")
		public Money getUnitPrice() {
			return unitPrice;
		}
		
		@Override
		public UnitContractValuationModel build() {
			return this;
		}
		
		@Override
		public UnitContractValuationModel.UnitContractValuationModelBuilder toBuilder() {
			UnitContractValuationModel.UnitContractValuationModelBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(UnitContractValuationModel.UnitContractValuationModelBuilder builder) {
			ofNullable(getNumberOfUnits()).ifPresent(builder::setNumberOfUnits);
			ofNullable(getUnitPrice()).ifPresent(builder::setUnitPrice);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			UnitContractValuationModel _that = getType().cast(o);
		
			if (!Objects.equals(numberOfUnits, _that.getNumberOfUnits())) return false;
			if (!Objects.equals(unitPrice, _that.getUnitPrice())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (numberOfUnits != null ? numberOfUnits.hashCode() : 0);
			_result = 31 * _result + (unitPrice != null ? unitPrice.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "UnitContractValuationModel {" +
				"numberOfUnits=" + this.numberOfUnits + ", " +
				"unitPrice=" + this.unitPrice +
			'}';
		}
	}

	/*********************** Builder Implementation of UnitContractValuationModel  ***********************/
	class UnitContractValuationModelBuilderImpl implements UnitContractValuationModel.UnitContractValuationModelBuilder {
	
		protected Quantity.QuantityBuilder numberOfUnits;
		protected Money.MoneyBuilder unitPrice;
	
		public UnitContractValuationModelBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("numberOfUnits")
		public Quantity.QuantityBuilder getNumberOfUnits() {
			return numberOfUnits;
		}
		
		@Override
		public Quantity.QuantityBuilder getOrCreateNumberOfUnits() {
			Quantity.QuantityBuilder result;
			if (numberOfUnits!=null) {
				result = numberOfUnits;
			}
			else {
				result = numberOfUnits = Quantity.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("unitPrice")
		public Money.MoneyBuilder getUnitPrice() {
			return unitPrice;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateUnitPrice() {
			Money.MoneyBuilder result;
			if (unitPrice!=null) {
				result = unitPrice;
			}
			else {
				result = unitPrice = Money.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("numberOfUnits")
		public UnitContractValuationModel.UnitContractValuationModelBuilder setNumberOfUnits(Quantity numberOfUnits) {
			this.numberOfUnits = numberOfUnits==null?null:numberOfUnits.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("unitPrice")
		public UnitContractValuationModel.UnitContractValuationModelBuilder setUnitPrice(Money unitPrice) {
			this.unitPrice = unitPrice==null?null:unitPrice.toBuilder();
			return this;
		}
		
		@Override
		public UnitContractValuationModel build() {
			return new UnitContractValuationModel.UnitContractValuationModelImpl(this);
		}
		
		@Override
		public UnitContractValuationModel.UnitContractValuationModelBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public UnitContractValuationModel.UnitContractValuationModelBuilder prune() {
			if (numberOfUnits!=null && !numberOfUnits.prune().hasData()) numberOfUnits = null;
			if (unitPrice!=null && !unitPrice.prune().hasData()) unitPrice = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getNumberOfUnits()!=null && getNumberOfUnits().hasData()) return true;
			if (getUnitPrice()!=null && getUnitPrice().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public UnitContractValuationModel.UnitContractValuationModelBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			UnitContractValuationModel.UnitContractValuationModelBuilder o = (UnitContractValuationModel.UnitContractValuationModelBuilder) other;
			
			merger.mergeRosetta(getNumberOfUnits(), o.getNumberOfUnits(), this::setNumberOfUnits);
			merger.mergeRosetta(getUnitPrice(), o.getUnitPrice(), this::setUnitPrice);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			UnitContractValuationModel _that = getType().cast(o);
		
			if (!Objects.equals(numberOfUnits, _that.getNumberOfUnits())) return false;
			if (!Objects.equals(unitPrice, _that.getUnitPrice())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (numberOfUnits != null ? numberOfUnits.hashCode() : 0);
			_result = 31 * _result + (unitPrice != null ? unitPrice.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "UnitContractValuationModelBuilder {" +
				"numberOfUnits=" + this.numberOfUnits + ", " +
				"unitPrice=" + this.unitPrice +
			'}';
		}
	}
}
