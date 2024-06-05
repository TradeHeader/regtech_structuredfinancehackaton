package cdm.event.common;

import cdm.event.common.CollateralPosition;
import cdm.event.common.CollateralPosition.CollateralPositionBuilder;
import cdm.event.common.CollateralPosition.CollateralPositionBuilderImpl;
import cdm.event.common.CollateralPosition.CollateralPositionImpl;
import cdm.event.common.CollateralStatusEnum;
import cdm.event.common.TradeState;
import cdm.event.common.meta.CollateralPositionMeta;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import cdm.event.common.metafields.ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder;
import cdm.event.position.Position;
import cdm.event.position.Position.PositionBuilder;
import cdm.event.position.Position.PositionBuilderImpl;
import cdm.event.position.Position.PositionImpl;
import cdm.observable.asset.Money;
import cdm.product.collateral.CollateralTreatment;
import cdm.product.common.settlement.PriceQuantity;
import cdm.product.template.Product;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Specifies the individual components of collateral positions.
 * @version ${project.version}
 */
@RosettaDataType(value="CollateralPosition", builder=CollateralPosition.CollateralPositionBuilderImpl.class, version="${project.version}")
public interface CollateralPosition extends Position {

	CollateralPositionMeta metaData = new CollateralPositionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies if there is any treatment to be applied to collateral, such as percentage discount which will impact collateral value.
	 */
	CollateralTreatment getTreatment();
	/**
	 * Indicates the collateral positions settlement status.
	 */
	CollateralStatusEnum getCollateralPositionStatus();

	/*********************** Build Methods  ***********************/
	CollateralPosition build();
	
	CollateralPosition.CollateralPositionBuilder toBuilder();
	
	static CollateralPosition.CollateralPositionBuilder builder() {
		return new CollateralPosition.CollateralPositionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CollateralPosition> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CollateralPosition> getType() {
		return CollateralPosition.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("priceQuantity"), processor, PriceQuantity.class, getPriceQuantity());
		processRosetta(path.newSubPath("product"), processor, Product.class, getProduct());
		processRosetta(path.newSubPath("cashBalance"), processor, Money.class, getCashBalance());
		processRosetta(path.newSubPath("tradeReference"), processor, ReferenceWithMetaTradeState.class, getTradeReference());
		processRosetta(path.newSubPath("treatment"), processor, CollateralTreatment.class, getTreatment());
		processor.processBasic(path.newSubPath("collateralPositionStatus"), CollateralStatusEnum.class, getCollateralPositionStatus(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface CollateralPositionBuilder extends CollateralPosition, Position.PositionBuilder, RosettaModelObjectBuilder {
		CollateralTreatment.CollateralTreatmentBuilder getOrCreateTreatment();
		CollateralTreatment.CollateralTreatmentBuilder getTreatment();
		CollateralPosition.CollateralPositionBuilder addPriceQuantity(PriceQuantity priceQuantity0);
		CollateralPosition.CollateralPositionBuilder addPriceQuantity(PriceQuantity priceQuantity1, int _idx);
		CollateralPosition.CollateralPositionBuilder addPriceQuantity(List<? extends PriceQuantity> priceQuantity2);
		CollateralPosition.CollateralPositionBuilder setPriceQuantity(List<? extends PriceQuantity> priceQuantity3);
		CollateralPosition.CollateralPositionBuilder setProduct(Product product);
		CollateralPosition.CollateralPositionBuilder setCashBalance(Money cashBalance);
		CollateralPosition.CollateralPositionBuilder setTradeReference(ReferenceWithMetaTradeState tradeReference0);
		CollateralPosition.CollateralPositionBuilder setTradeReferenceValue(TradeState tradeReference1);
		CollateralPosition.CollateralPositionBuilder setTreatment(CollateralTreatment treatment);
		CollateralPosition.CollateralPositionBuilder setCollateralPositionStatus(CollateralStatusEnum collateralPositionStatus);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("priceQuantity"), processor, PriceQuantity.PriceQuantityBuilder.class, getPriceQuantity());
			processRosetta(path.newSubPath("product"), processor, Product.ProductBuilder.class, getProduct());
			processRosetta(path.newSubPath("cashBalance"), processor, Money.MoneyBuilder.class, getCashBalance());
			processRosetta(path.newSubPath("tradeReference"), processor, ReferenceWithMetaTradeState.ReferenceWithMetaTradeStateBuilder.class, getTradeReference());
			processRosetta(path.newSubPath("treatment"), processor, CollateralTreatment.CollateralTreatmentBuilder.class, getTreatment());
			processor.processBasic(path.newSubPath("collateralPositionStatus"), CollateralStatusEnum.class, getCollateralPositionStatus(), this);
		}
		

		CollateralPosition.CollateralPositionBuilder prune();
	}

	/*********************** Immutable Implementation of CollateralPosition  ***********************/
	class CollateralPositionImpl extends Position.PositionImpl implements CollateralPosition {
		private final CollateralTreatment treatment;
		private final CollateralStatusEnum collateralPositionStatus;
		
		protected CollateralPositionImpl(CollateralPosition.CollateralPositionBuilder builder) {
			super(builder);
			this.treatment = ofNullable(builder.getTreatment()).map(f->f.build()).orElse(null);
			this.collateralPositionStatus = builder.getCollateralPositionStatus();
		}
		
		@Override
		@RosettaAttribute("treatment")
		public CollateralTreatment getTreatment() {
			return treatment;
		}
		
		@Override
		@RosettaAttribute("collateralPositionStatus")
		public CollateralStatusEnum getCollateralPositionStatus() {
			return collateralPositionStatus;
		}
		
		@Override
		public CollateralPosition build() {
			return this;
		}
		
		@Override
		public CollateralPosition.CollateralPositionBuilder toBuilder() {
			CollateralPosition.CollateralPositionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CollateralPosition.CollateralPositionBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getTreatment()).ifPresent(builder::setTreatment);
			ofNullable(getCollateralPositionStatus()).ifPresent(builder::setCollateralPositionStatus);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			CollateralPosition _that = getType().cast(o);
		
			if (!Objects.equals(treatment, _that.getTreatment())) return false;
			if (!Objects.equals(collateralPositionStatus, _that.getCollateralPositionStatus())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (treatment != null ? treatment.hashCode() : 0);
			_result = 31 * _result + (collateralPositionStatus != null ? collateralPositionStatus.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralPosition {" +
				"treatment=" + this.treatment + ", " +
				"collateralPositionStatus=" + this.collateralPositionStatus +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of CollateralPosition  ***********************/
	class CollateralPositionBuilderImpl extends Position.PositionBuilderImpl  implements CollateralPosition.CollateralPositionBuilder {
	
		protected CollateralTreatment.CollateralTreatmentBuilder treatment;
		protected CollateralStatusEnum collateralPositionStatus;
	
		public CollateralPositionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("treatment")
		public CollateralTreatment.CollateralTreatmentBuilder getTreatment() {
			return treatment;
		}
		
		@Override
		public CollateralTreatment.CollateralTreatmentBuilder getOrCreateTreatment() {
			CollateralTreatment.CollateralTreatmentBuilder result;
			if (treatment!=null) {
				result = treatment;
			}
			else {
				result = treatment = CollateralTreatment.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("collateralPositionStatus")
		public CollateralStatusEnum getCollateralPositionStatus() {
			return collateralPositionStatus;
		}
		
	
		@Override
		public CollateralPosition.CollateralPositionBuilder addPriceQuantity(PriceQuantity priceQuantity) {
			if (priceQuantity!=null) this.priceQuantity.add(priceQuantity.toBuilder());
			return this;
		}
		
		@Override
		public CollateralPosition.CollateralPositionBuilder addPriceQuantity(PriceQuantity priceQuantity, int _idx) {
			getIndex(this.priceQuantity, _idx, () -> priceQuantity.toBuilder());
			return this;
		}
		@Override 
		public CollateralPosition.CollateralPositionBuilder addPriceQuantity(List<? extends PriceQuantity> priceQuantitys) {
			if (priceQuantitys != null) {
				for (PriceQuantity toAdd : priceQuantitys) {
					this.priceQuantity.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("priceQuantity")
		public CollateralPosition.CollateralPositionBuilder setPriceQuantity(List<? extends PriceQuantity> priceQuantitys) {
			if (priceQuantitys == null)  {
				this.priceQuantity = new ArrayList<>();
			}
			else {
				this.priceQuantity = priceQuantitys.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("product")
		public CollateralPosition.CollateralPositionBuilder setProduct(Product product) {
			this.product = product==null?null:product.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("cashBalance")
		public CollateralPosition.CollateralPositionBuilder setCashBalance(Money cashBalance) {
			this.cashBalance = cashBalance==null?null:cashBalance.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("tradeReference")
		public CollateralPosition.CollateralPositionBuilder setTradeReference(ReferenceWithMetaTradeState tradeReference) {
			this.tradeReference = tradeReference==null?null:tradeReference.toBuilder();
			return this;
		}
		@Override
		public CollateralPosition.CollateralPositionBuilder setTradeReferenceValue(TradeState tradeReference) {
			this.getOrCreateTradeReference().setValue(tradeReference);
			return this;
		}
		@Override
		@RosettaAttribute("treatment")
		public CollateralPosition.CollateralPositionBuilder setTreatment(CollateralTreatment treatment) {
			this.treatment = treatment==null?null:treatment.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("collateralPositionStatus")
		public CollateralPosition.CollateralPositionBuilder setCollateralPositionStatus(CollateralStatusEnum collateralPositionStatus) {
			this.collateralPositionStatus = collateralPositionStatus==null?null:collateralPositionStatus;
			return this;
		}
		
		@Override
		public CollateralPosition build() {
			return new CollateralPosition.CollateralPositionImpl(this);
		}
		
		@Override
		public CollateralPosition.CollateralPositionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralPosition.CollateralPositionBuilder prune() {
			super.prune();
			if (treatment!=null && !treatment.prune().hasData()) treatment = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getTreatment()!=null && getTreatment().hasData()) return true;
			if (getCollateralPositionStatus()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralPosition.CollateralPositionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			CollateralPosition.CollateralPositionBuilder o = (CollateralPosition.CollateralPositionBuilder) other;
			
			merger.mergeRosetta(getTreatment(), o.getTreatment(), this::setTreatment);
			
			merger.mergeBasic(getCollateralPositionStatus(), o.getCollateralPositionStatus(), this::setCollateralPositionStatus);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			CollateralPosition _that = getType().cast(o);
		
			if (!Objects.equals(treatment, _that.getTreatment())) return false;
			if (!Objects.equals(collateralPositionStatus, _that.getCollateralPositionStatus())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (treatment != null ? treatment.hashCode() : 0);
			_result = 31 * _result + (collateralPositionStatus != null ? collateralPositionStatus.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralPositionBuilder {" +
				"treatment=" + this.treatment + ", " +
				"collateralPositionStatus=" + this.collateralPositionStatus +
			'}' + " " + super.toString();
		}
	}
}
