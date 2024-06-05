package cdm.observable.asset;

import cdm.base.staticdata.asset.common.Equity;
import cdm.observable.asset.BondChoiceModel;
import cdm.observable.asset.BondEquityModel;
import cdm.observable.asset.BondEquityModel.BondEquityModelBuilder;
import cdm.observable.asset.BondEquityModel.BondEquityModelBuilderImpl;
import cdm.observable.asset.BondEquityModel.BondEquityModelImpl;
import cdm.observable.asset.meta.BondEquityModelMeta;
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
 *  Bond equity model to value convertible bonds and modelled onto BondEquity.model in FpML.
 * @version ${project.version}
 */
@RosettaDataType(value="BondEquityModel", builder=BondEquityModel.BondEquityModelBuilderImpl.class, version="${project.version}")
public interface BondEquityModel extends RosettaModelObject {

	BondEquityModelMeta metaData = new BondEquityModelMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Either the bond or convertible bond.
	 */
	BondChoiceModel getBondchoiceModel();
	/**
	 * The equity.
	 */
	Equity getEquity();

	/*********************** Build Methods  ***********************/
	BondEquityModel build();
	
	BondEquityModel.BondEquityModelBuilder toBuilder();
	
	static BondEquityModel.BondEquityModelBuilder builder() {
		return new BondEquityModel.BondEquityModelBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends BondEquityModel> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends BondEquityModel> getType() {
		return BondEquityModel.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("bondchoiceModel"), processor, BondChoiceModel.class, getBondchoiceModel());
		processRosetta(path.newSubPath("equity"), processor, Equity.class, getEquity());
	}
	

	/*********************** Builder Interface  ***********************/
	interface BondEquityModelBuilder extends BondEquityModel, RosettaModelObjectBuilder {
		BondChoiceModel.BondChoiceModelBuilder getOrCreateBondchoiceModel();
		BondChoiceModel.BondChoiceModelBuilder getBondchoiceModel();
		Equity.EquityBuilder getOrCreateEquity();
		Equity.EquityBuilder getEquity();
		BondEquityModel.BondEquityModelBuilder setBondchoiceModel(BondChoiceModel bondchoiceModel);
		BondEquityModel.BondEquityModelBuilder setEquity(Equity equity);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("bondchoiceModel"), processor, BondChoiceModel.BondChoiceModelBuilder.class, getBondchoiceModel());
			processRosetta(path.newSubPath("equity"), processor, Equity.EquityBuilder.class, getEquity());
		}
		

		BondEquityModel.BondEquityModelBuilder prune();
	}

	/*********************** Immutable Implementation of BondEquityModel  ***********************/
	class BondEquityModelImpl implements BondEquityModel {
		private final BondChoiceModel bondchoiceModel;
		private final Equity equity;
		
		protected BondEquityModelImpl(BondEquityModel.BondEquityModelBuilder builder) {
			this.bondchoiceModel = ofNullable(builder.getBondchoiceModel()).map(f->f.build()).orElse(null);
			this.equity = ofNullable(builder.getEquity()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("bondchoiceModel")
		public BondChoiceModel getBondchoiceModel() {
			return bondchoiceModel;
		}
		
		@Override
		@RosettaAttribute("equity")
		public Equity getEquity() {
			return equity;
		}
		
		@Override
		public BondEquityModel build() {
			return this;
		}
		
		@Override
		public BondEquityModel.BondEquityModelBuilder toBuilder() {
			BondEquityModel.BondEquityModelBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(BondEquityModel.BondEquityModelBuilder builder) {
			ofNullable(getBondchoiceModel()).ifPresent(builder::setBondchoiceModel);
			ofNullable(getEquity()).ifPresent(builder::setEquity);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			BondEquityModel _that = getType().cast(o);
		
			if (!Objects.equals(bondchoiceModel, _that.getBondchoiceModel())) return false;
			if (!Objects.equals(equity, _that.getEquity())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (bondchoiceModel != null ? bondchoiceModel.hashCode() : 0);
			_result = 31 * _result + (equity != null ? equity.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BondEquityModel {" +
				"bondchoiceModel=" + this.bondchoiceModel + ", " +
				"equity=" + this.equity +
			'}';
		}
	}

	/*********************** Builder Implementation of BondEquityModel  ***********************/
	class BondEquityModelBuilderImpl implements BondEquityModel.BondEquityModelBuilder {
	
		protected BondChoiceModel.BondChoiceModelBuilder bondchoiceModel;
		protected Equity.EquityBuilder equity;
	
		public BondEquityModelBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("bondchoiceModel")
		public BondChoiceModel.BondChoiceModelBuilder getBondchoiceModel() {
			return bondchoiceModel;
		}
		
		@Override
		public BondChoiceModel.BondChoiceModelBuilder getOrCreateBondchoiceModel() {
			BondChoiceModel.BondChoiceModelBuilder result;
			if (bondchoiceModel!=null) {
				result = bondchoiceModel;
			}
			else {
				result = bondchoiceModel = BondChoiceModel.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("equity")
		public Equity.EquityBuilder getEquity() {
			return equity;
		}
		
		@Override
		public Equity.EquityBuilder getOrCreateEquity() {
			Equity.EquityBuilder result;
			if (equity!=null) {
				result = equity;
			}
			else {
				result = equity = Equity.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("bondchoiceModel")
		public BondEquityModel.BondEquityModelBuilder setBondchoiceModel(BondChoiceModel bondchoiceModel) {
			this.bondchoiceModel = bondchoiceModel==null?null:bondchoiceModel.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("equity")
		public BondEquityModel.BondEquityModelBuilder setEquity(Equity equity) {
			this.equity = equity==null?null:equity.toBuilder();
			return this;
		}
		
		@Override
		public BondEquityModel build() {
			return new BondEquityModel.BondEquityModelImpl(this);
		}
		
		@Override
		public BondEquityModel.BondEquityModelBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BondEquityModel.BondEquityModelBuilder prune() {
			if (bondchoiceModel!=null && !bondchoiceModel.prune().hasData()) bondchoiceModel = null;
			if (equity!=null && !equity.prune().hasData()) equity = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getBondchoiceModel()!=null && getBondchoiceModel().hasData()) return true;
			if (getEquity()!=null && getEquity().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BondEquityModel.BondEquityModelBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			BondEquityModel.BondEquityModelBuilder o = (BondEquityModel.BondEquityModelBuilder) other;
			
			merger.mergeRosetta(getBondchoiceModel(), o.getBondchoiceModel(), this::setBondchoiceModel);
			merger.mergeRosetta(getEquity(), o.getEquity(), this::setEquity);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			BondEquityModel _that = getType().cast(o);
		
			if (!Objects.equals(bondchoiceModel, _that.getBondchoiceModel())) return false;
			if (!Objects.equals(equity, _that.getEquity())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (bondchoiceModel != null ? bondchoiceModel.hashCode() : 0);
			_result = 31 * _result + (equity != null ? equity.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BondEquityModelBuilder {" +
				"bondchoiceModel=" + this.bondchoiceModel + ", " +
				"equity=" + this.equity +
			'}';
		}
	}
}
