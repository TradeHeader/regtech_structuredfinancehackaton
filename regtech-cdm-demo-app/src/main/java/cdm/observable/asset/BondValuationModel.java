package cdm.observable.asset;

import cdm.observable.asset.BondPriceAndYieldModel;
import cdm.observable.asset.BondValuationModel;
import cdm.observable.asset.BondValuationModel.BondValuationModelBuilder;
import cdm.observable.asset.BondValuationModel.BondValuationModelBuilderImpl;
import cdm.observable.asset.BondValuationModel.BondValuationModelImpl;
import cdm.observable.asset.Money;
import cdm.observable.asset.meta.BondValuationModelMeta;
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
 *  Bond valuation model for the security leg in a securities financing transaction, closely modelled onto the BondCollateral.model in FpML.
 * @version ${project.version}
 */
@RosettaDataType(value="BondValuationModel", builder=BondValuationModel.BondValuationModelBuilderImpl.class, version="${project.version}")
public interface BondValuationModel extends RosettaModelObject {

	BondValuationModelMeta metaData = new BondValuationModelMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The quantity of the underlier expressed as a nominal amount.
	 */
	Money getNominalAmount();
	/**
	 * Price and yield model for valuing a bond security leg.
	 */
	BondPriceAndYieldModel getBondPriceAndYieldModel();
	/**
	 * Accruals amount for the bond in the security leg
	 */
	Money getAccrualsAmount();

	/*********************** Build Methods  ***********************/
	BondValuationModel build();
	
	BondValuationModel.BondValuationModelBuilder toBuilder();
	
	static BondValuationModel.BondValuationModelBuilder builder() {
		return new BondValuationModel.BondValuationModelBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends BondValuationModel> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends BondValuationModel> getType() {
		return BondValuationModel.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("nominalAmount"), processor, Money.class, getNominalAmount());
		processRosetta(path.newSubPath("bondPriceAndYieldModel"), processor, BondPriceAndYieldModel.class, getBondPriceAndYieldModel());
		processRosetta(path.newSubPath("accrualsAmount"), processor, Money.class, getAccrualsAmount());
	}
	

	/*********************** Builder Interface  ***********************/
	interface BondValuationModelBuilder extends BondValuationModel, RosettaModelObjectBuilder {
		Money.MoneyBuilder getOrCreateNominalAmount();
		Money.MoneyBuilder getNominalAmount();
		BondPriceAndYieldModel.BondPriceAndYieldModelBuilder getOrCreateBondPriceAndYieldModel();
		BondPriceAndYieldModel.BondPriceAndYieldModelBuilder getBondPriceAndYieldModel();
		Money.MoneyBuilder getOrCreateAccrualsAmount();
		Money.MoneyBuilder getAccrualsAmount();
		BondValuationModel.BondValuationModelBuilder setNominalAmount(Money nominalAmount);
		BondValuationModel.BondValuationModelBuilder setBondPriceAndYieldModel(BondPriceAndYieldModel bondPriceAndYieldModel);
		BondValuationModel.BondValuationModelBuilder setAccrualsAmount(Money accrualsAmount);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("nominalAmount"), processor, Money.MoneyBuilder.class, getNominalAmount());
			processRosetta(path.newSubPath("bondPriceAndYieldModel"), processor, BondPriceAndYieldModel.BondPriceAndYieldModelBuilder.class, getBondPriceAndYieldModel());
			processRosetta(path.newSubPath("accrualsAmount"), processor, Money.MoneyBuilder.class, getAccrualsAmount());
		}
		

		BondValuationModel.BondValuationModelBuilder prune();
	}

	/*********************** Immutable Implementation of BondValuationModel  ***********************/
	class BondValuationModelImpl implements BondValuationModel {
		private final Money nominalAmount;
		private final BondPriceAndYieldModel bondPriceAndYieldModel;
		private final Money accrualsAmount;
		
		protected BondValuationModelImpl(BondValuationModel.BondValuationModelBuilder builder) {
			this.nominalAmount = ofNullable(builder.getNominalAmount()).map(f->f.build()).orElse(null);
			this.bondPriceAndYieldModel = ofNullable(builder.getBondPriceAndYieldModel()).map(f->f.build()).orElse(null);
			this.accrualsAmount = ofNullable(builder.getAccrualsAmount()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("nominalAmount")
		public Money getNominalAmount() {
			return nominalAmount;
		}
		
		@Override
		@RosettaAttribute("bondPriceAndYieldModel")
		public BondPriceAndYieldModel getBondPriceAndYieldModel() {
			return bondPriceAndYieldModel;
		}
		
		@Override
		@RosettaAttribute("accrualsAmount")
		public Money getAccrualsAmount() {
			return accrualsAmount;
		}
		
		@Override
		public BondValuationModel build() {
			return this;
		}
		
		@Override
		public BondValuationModel.BondValuationModelBuilder toBuilder() {
			BondValuationModel.BondValuationModelBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(BondValuationModel.BondValuationModelBuilder builder) {
			ofNullable(getNominalAmount()).ifPresent(builder::setNominalAmount);
			ofNullable(getBondPriceAndYieldModel()).ifPresent(builder::setBondPriceAndYieldModel);
			ofNullable(getAccrualsAmount()).ifPresent(builder::setAccrualsAmount);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			BondValuationModel _that = getType().cast(o);
		
			if (!Objects.equals(nominalAmount, _that.getNominalAmount())) return false;
			if (!Objects.equals(bondPriceAndYieldModel, _that.getBondPriceAndYieldModel())) return false;
			if (!Objects.equals(accrualsAmount, _that.getAccrualsAmount())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (nominalAmount != null ? nominalAmount.hashCode() : 0);
			_result = 31 * _result + (bondPriceAndYieldModel != null ? bondPriceAndYieldModel.hashCode() : 0);
			_result = 31 * _result + (accrualsAmount != null ? accrualsAmount.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BondValuationModel {" +
				"nominalAmount=" + this.nominalAmount + ", " +
				"bondPriceAndYieldModel=" + this.bondPriceAndYieldModel + ", " +
				"accrualsAmount=" + this.accrualsAmount +
			'}';
		}
	}

	/*********************** Builder Implementation of BondValuationModel  ***********************/
	class BondValuationModelBuilderImpl implements BondValuationModel.BondValuationModelBuilder {
	
		protected Money.MoneyBuilder nominalAmount;
		protected BondPriceAndYieldModel.BondPriceAndYieldModelBuilder bondPriceAndYieldModel;
		protected Money.MoneyBuilder accrualsAmount;
	
		public BondValuationModelBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("nominalAmount")
		public Money.MoneyBuilder getNominalAmount() {
			return nominalAmount;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateNominalAmount() {
			Money.MoneyBuilder result;
			if (nominalAmount!=null) {
				result = nominalAmount;
			}
			else {
				result = nominalAmount = Money.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("bondPriceAndYieldModel")
		public BondPriceAndYieldModel.BondPriceAndYieldModelBuilder getBondPriceAndYieldModel() {
			return bondPriceAndYieldModel;
		}
		
		@Override
		public BondPriceAndYieldModel.BondPriceAndYieldModelBuilder getOrCreateBondPriceAndYieldModel() {
			BondPriceAndYieldModel.BondPriceAndYieldModelBuilder result;
			if (bondPriceAndYieldModel!=null) {
				result = bondPriceAndYieldModel;
			}
			else {
				result = bondPriceAndYieldModel = BondPriceAndYieldModel.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("accrualsAmount")
		public Money.MoneyBuilder getAccrualsAmount() {
			return accrualsAmount;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateAccrualsAmount() {
			Money.MoneyBuilder result;
			if (accrualsAmount!=null) {
				result = accrualsAmount;
			}
			else {
				result = accrualsAmount = Money.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("nominalAmount")
		public BondValuationModel.BondValuationModelBuilder setNominalAmount(Money nominalAmount) {
			this.nominalAmount = nominalAmount==null?null:nominalAmount.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("bondPriceAndYieldModel")
		public BondValuationModel.BondValuationModelBuilder setBondPriceAndYieldModel(BondPriceAndYieldModel bondPriceAndYieldModel) {
			this.bondPriceAndYieldModel = bondPriceAndYieldModel==null?null:bondPriceAndYieldModel.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("accrualsAmount")
		public BondValuationModel.BondValuationModelBuilder setAccrualsAmount(Money accrualsAmount) {
			this.accrualsAmount = accrualsAmount==null?null:accrualsAmount.toBuilder();
			return this;
		}
		
		@Override
		public BondValuationModel build() {
			return new BondValuationModel.BondValuationModelImpl(this);
		}
		
		@Override
		public BondValuationModel.BondValuationModelBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BondValuationModel.BondValuationModelBuilder prune() {
			if (nominalAmount!=null && !nominalAmount.prune().hasData()) nominalAmount = null;
			if (bondPriceAndYieldModel!=null && !bondPriceAndYieldModel.prune().hasData()) bondPriceAndYieldModel = null;
			if (accrualsAmount!=null && !accrualsAmount.prune().hasData()) accrualsAmount = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getNominalAmount()!=null && getNominalAmount().hasData()) return true;
			if (getBondPriceAndYieldModel()!=null && getBondPriceAndYieldModel().hasData()) return true;
			if (getAccrualsAmount()!=null && getAccrualsAmount().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BondValuationModel.BondValuationModelBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			BondValuationModel.BondValuationModelBuilder o = (BondValuationModel.BondValuationModelBuilder) other;
			
			merger.mergeRosetta(getNominalAmount(), o.getNominalAmount(), this::setNominalAmount);
			merger.mergeRosetta(getBondPriceAndYieldModel(), o.getBondPriceAndYieldModel(), this::setBondPriceAndYieldModel);
			merger.mergeRosetta(getAccrualsAmount(), o.getAccrualsAmount(), this::setAccrualsAmount);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			BondValuationModel _that = getType().cast(o);
		
			if (!Objects.equals(nominalAmount, _that.getNominalAmount())) return false;
			if (!Objects.equals(bondPriceAndYieldModel, _that.getBondPriceAndYieldModel())) return false;
			if (!Objects.equals(accrualsAmount, _that.getAccrualsAmount())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (nominalAmount != null ? nominalAmount.hashCode() : 0);
			_result = 31 * _result + (bondPriceAndYieldModel != null ? bondPriceAndYieldModel.hashCode() : 0);
			_result = 31 * _result + (accrualsAmount != null ? accrualsAmount.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BondValuationModelBuilder {" +
				"nominalAmount=" + this.nominalAmount + ", " +
				"bondPriceAndYieldModel=" + this.bondPriceAndYieldModel + ", " +
				"accrualsAmount=" + this.accrualsAmount +
			'}';
		}
	}
}
