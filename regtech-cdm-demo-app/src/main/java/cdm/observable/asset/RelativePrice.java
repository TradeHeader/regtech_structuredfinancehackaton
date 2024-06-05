package cdm.observable.asset;

import cdm.observable.asset.BondEquityModel;
import cdm.observable.asset.RelativePrice;
import cdm.observable.asset.RelativePrice.RelativePriceBuilder;
import cdm.observable.asset.RelativePrice.RelativePriceBuilderImpl;
import cdm.observable.asset.RelativePrice.RelativePriceImpl;
import cdm.observable.asset.meta.RelativePriceMeta;
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
 *  Bond price relative to a benchmark, as in a convertible bond.
 * @version ${project.version}
 */
@RosettaDataType(value="RelativePrice", builder=RelativePrice.RelativePriceBuilderImpl.class, version="${project.version}")
public interface RelativePrice extends RosettaModelObject {

	RelativePriceMeta metaData = new RelativePriceMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The spread to a benchmark.
	 */
	BigDecimal getSpread();
	/**
	 * Bond equity model for convertible bonds.
	 */
	List<? extends BondEquityModel> getBondEquityModel();

	/*********************** Build Methods  ***********************/
	RelativePrice build();
	
	RelativePrice.RelativePriceBuilder toBuilder();
	
	static RelativePrice.RelativePriceBuilder builder() {
		return new RelativePrice.RelativePriceBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends RelativePrice> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends RelativePrice> getType() {
		return RelativePrice.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("spread"), BigDecimal.class, getSpread(), this);
		processRosetta(path.newSubPath("bondEquityModel"), processor, BondEquityModel.class, getBondEquityModel());
	}
	

	/*********************** Builder Interface  ***********************/
	interface RelativePriceBuilder extends RelativePrice, RosettaModelObjectBuilder {
		BondEquityModel.BondEquityModelBuilder getOrCreateBondEquityModel(int _index);
		List<? extends BondEquityModel.BondEquityModelBuilder> getBondEquityModel();
		RelativePrice.RelativePriceBuilder setSpread(BigDecimal spread);
		RelativePrice.RelativePriceBuilder addBondEquityModel(BondEquityModel bondEquityModel0);
		RelativePrice.RelativePriceBuilder addBondEquityModel(BondEquityModel bondEquityModel1, int _idx);
		RelativePrice.RelativePriceBuilder addBondEquityModel(List<? extends BondEquityModel> bondEquityModel2);
		RelativePrice.RelativePriceBuilder setBondEquityModel(List<? extends BondEquityModel> bondEquityModel3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("spread"), BigDecimal.class, getSpread(), this);
			processRosetta(path.newSubPath("bondEquityModel"), processor, BondEquityModel.BondEquityModelBuilder.class, getBondEquityModel());
		}
		

		RelativePrice.RelativePriceBuilder prune();
	}

	/*********************** Immutable Implementation of RelativePrice  ***********************/
	class RelativePriceImpl implements RelativePrice {
		private final BigDecimal spread;
		private final List<? extends BondEquityModel> bondEquityModel;
		
		protected RelativePriceImpl(RelativePrice.RelativePriceBuilder builder) {
			this.spread = builder.getSpread();
			this.bondEquityModel = ofNullable(builder.getBondEquityModel()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("spread")
		public BigDecimal getSpread() {
			return spread;
		}
		
		@Override
		@RosettaAttribute("bondEquityModel")
		public List<? extends BondEquityModel> getBondEquityModel() {
			return bondEquityModel;
		}
		
		@Override
		public RelativePrice build() {
			return this;
		}
		
		@Override
		public RelativePrice.RelativePriceBuilder toBuilder() {
			RelativePrice.RelativePriceBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(RelativePrice.RelativePriceBuilder builder) {
			ofNullable(getSpread()).ifPresent(builder::setSpread);
			ofNullable(getBondEquityModel()).ifPresent(builder::setBondEquityModel);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			RelativePrice _that = getType().cast(o);
		
			if (!Objects.equals(spread, _that.getSpread())) return false;
			if (!ListEquals.listEquals(bondEquityModel, _that.getBondEquityModel())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (spread != null ? spread.hashCode() : 0);
			_result = 31 * _result + (bondEquityModel != null ? bondEquityModel.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "RelativePrice {" +
				"spread=" + this.spread + ", " +
				"bondEquityModel=" + this.bondEquityModel +
			'}';
		}
	}

	/*********************** Builder Implementation of RelativePrice  ***********************/
	class RelativePriceBuilderImpl implements RelativePrice.RelativePriceBuilder {
	
		protected BigDecimal spread;
		protected List<BondEquityModel.BondEquityModelBuilder> bondEquityModel = new ArrayList<>();
	
		public RelativePriceBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("spread")
		public BigDecimal getSpread() {
			return spread;
		}
		
		@Override
		@RosettaAttribute("bondEquityModel")
		public List<? extends BondEquityModel.BondEquityModelBuilder> getBondEquityModel() {
			return bondEquityModel;
		}
		
		public BondEquityModel.BondEquityModelBuilder getOrCreateBondEquityModel(int _index) {
		
			if (bondEquityModel==null) {
				this.bondEquityModel = new ArrayList<>();
			}
			BondEquityModel.BondEquityModelBuilder result;
			return getIndex(bondEquityModel, _index, () -> {
						BondEquityModel.BondEquityModelBuilder newBondEquityModel = BondEquityModel.builder();
						return newBondEquityModel;
					});
		}
		
	
		@Override
		@RosettaAttribute("spread")
		public RelativePrice.RelativePriceBuilder setSpread(BigDecimal spread) {
			this.spread = spread==null?null:spread;
			return this;
		}
		@Override
		public RelativePrice.RelativePriceBuilder addBondEquityModel(BondEquityModel bondEquityModel) {
			if (bondEquityModel!=null) this.bondEquityModel.add(bondEquityModel.toBuilder());
			return this;
		}
		
		@Override
		public RelativePrice.RelativePriceBuilder addBondEquityModel(BondEquityModel bondEquityModel, int _idx) {
			getIndex(this.bondEquityModel, _idx, () -> bondEquityModel.toBuilder());
			return this;
		}
		@Override 
		public RelativePrice.RelativePriceBuilder addBondEquityModel(List<? extends BondEquityModel> bondEquityModels) {
			if (bondEquityModels != null) {
				for (BondEquityModel toAdd : bondEquityModels) {
					this.bondEquityModel.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("bondEquityModel")
		public RelativePrice.RelativePriceBuilder setBondEquityModel(List<? extends BondEquityModel> bondEquityModels) {
			if (bondEquityModels == null)  {
				this.bondEquityModel = new ArrayList<>();
			}
			else {
				this.bondEquityModel = bondEquityModels.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public RelativePrice build() {
			return new RelativePrice.RelativePriceImpl(this);
		}
		
		@Override
		public RelativePrice.RelativePriceBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public RelativePrice.RelativePriceBuilder prune() {
			bondEquityModel = bondEquityModel.stream().filter(b->b!=null).<BondEquityModel.BondEquityModelBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getSpread()!=null) return true;
			if (getBondEquityModel()!=null && getBondEquityModel().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public RelativePrice.RelativePriceBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			RelativePrice.RelativePriceBuilder o = (RelativePrice.RelativePriceBuilder) other;
			
			merger.mergeRosetta(getBondEquityModel(), o.getBondEquityModel(), this::getOrCreateBondEquityModel);
			
			merger.mergeBasic(getSpread(), o.getSpread(), this::setSpread);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			RelativePrice _that = getType().cast(o);
		
			if (!Objects.equals(spread, _that.getSpread())) return false;
			if (!ListEquals.listEquals(bondEquityModel, _that.getBondEquityModel())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (spread != null ? spread.hashCode() : 0);
			_result = 31 * _result + (bondEquityModel != null ? bondEquityModel.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "RelativePriceBuilder {" +
				"spread=" + this.spread + ", " +
				"bondEquityModel=" + this.bondEquityModel +
			'}';
		}
	}
}
