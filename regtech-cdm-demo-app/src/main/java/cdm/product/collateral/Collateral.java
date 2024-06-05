package cdm.product.collateral;

import cdm.base.staticdata.identifier.Identifier;
import cdm.event.common.CollateralPortfolio;
import cdm.event.common.metafields.ReferenceWithMetaCollateralPortfolio;
import cdm.event.common.metafields.ReferenceWithMetaCollateralPortfolio.ReferenceWithMetaCollateralPortfolioBuilder;
import cdm.product.collateral.Collateral;
import cdm.product.collateral.Collateral.CollateralBuilder;
import cdm.product.collateral.Collateral.CollateralBuilderImpl;
import cdm.product.collateral.Collateral.CollateralImpl;
import cdm.product.collateral.CollateralProvisions;
import cdm.product.collateral.IndependentAmount;
import cdm.product.collateral.meta.CollateralMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.MetaFields;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A type for defining the obligations of the counterparty subject to credit support requirements.
 * @version ${project.version}
 */
@RosettaDataType(value="Collateral", builder=Collateral.CollateralBuilderImpl.class, version="${project.version}")
public interface Collateral extends RosettaModelObject, GlobalKey {

	CollateralMeta metaData = new CollateralMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Independent Amount is an amount that usually less creditworthy counterparties are asked to provide. It can either be a fixed amount or a percentage of the Transaction&#39;s value. The Independent Amount can be: (i) transferred before any trading between the parties occurs (as a deposit at a third party&#39;s account or with the counterparty) or (ii) callable after trading has occurred (typically because a downgrade has occurred). In situation (i), the Independent Amount is not included in the calculation of Exposure, but in situation (ii), it is included in the calculation of Exposure. Thus, for situation (ii), the Independent Amount may be transferred along with any collateral call. Independent Amount is a defined term in the ISDA Credit Support Annex. (&#39;with respect to a party, the amount specified as such for that party in Paragraph 13; if no amount is specified, zero&#39;).
	 */
	IndependentAmount getIndependentAmount();
	/**
	 * A list of identifiers pointing to the collateral portfolios which contain the collateral which covers a trade.
	 */
	List<? extends Identifier> getPortfolioIdentifier();
	/**
	 * The collateral portfolios which contain the collateral which covers a trade. (NB: this can be provided by reference to a global key for each CollateralPortfolio object)
	 */
	List<? extends ReferenceWithMetaCollateralPortfolio> getCollateralPortfolio();
	/**
	 * specifies the collateral provisions of the product.
	 */
	CollateralProvisions getCollateralProvisions();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	Collateral build();
	
	Collateral.CollateralBuilder toBuilder();
	
	static Collateral.CollateralBuilder builder() {
		return new Collateral.CollateralBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Collateral> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Collateral> getType() {
		return Collateral.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("independentAmount"), processor, IndependentAmount.class, getIndependentAmount());
		processRosetta(path.newSubPath("portfolioIdentifier"), processor, Identifier.class, getPortfolioIdentifier());
		processRosetta(path.newSubPath("collateralPortfolio"), processor, ReferenceWithMetaCollateralPortfolio.class, getCollateralPortfolio());
		processRosetta(path.newSubPath("collateralProvisions"), processor, CollateralProvisions.class, getCollateralProvisions());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CollateralBuilder extends Collateral, RosettaModelObjectBuilder {
		IndependentAmount.IndependentAmountBuilder getOrCreateIndependentAmount();
		IndependentAmount.IndependentAmountBuilder getIndependentAmount();
		Identifier.IdentifierBuilder getOrCreatePortfolioIdentifier(int _index);
		List<? extends Identifier.IdentifierBuilder> getPortfolioIdentifier();
		ReferenceWithMetaCollateralPortfolio.ReferenceWithMetaCollateralPortfolioBuilder getOrCreateCollateralPortfolio(int _index);
		List<? extends ReferenceWithMetaCollateralPortfolio.ReferenceWithMetaCollateralPortfolioBuilder> getCollateralPortfolio();
		CollateralProvisions.CollateralProvisionsBuilder getOrCreateCollateralProvisions();
		CollateralProvisions.CollateralProvisionsBuilder getCollateralProvisions();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		Collateral.CollateralBuilder setIndependentAmount(IndependentAmount independentAmount);
		Collateral.CollateralBuilder addPortfolioIdentifier(Identifier portfolioIdentifier0);
		Collateral.CollateralBuilder addPortfolioIdentifier(Identifier portfolioIdentifier1, int _idx);
		Collateral.CollateralBuilder addPortfolioIdentifier(List<? extends Identifier> portfolioIdentifier2);
		Collateral.CollateralBuilder setPortfolioIdentifier(List<? extends Identifier> portfolioIdentifier3);
		Collateral.CollateralBuilder addCollateralPortfolio(ReferenceWithMetaCollateralPortfolio collateralPortfolio0);
		Collateral.CollateralBuilder addCollateralPortfolio(ReferenceWithMetaCollateralPortfolio collateralPortfolio1, int _idx);
		Collateral.CollateralBuilder addCollateralPortfolioValue(CollateralPortfolio collateralPortfolio2);
		Collateral.CollateralBuilder addCollateralPortfolioValue(CollateralPortfolio collateralPortfolio3, int _idx);
		Collateral.CollateralBuilder addCollateralPortfolio(List<? extends ReferenceWithMetaCollateralPortfolio> collateralPortfolio4);
		Collateral.CollateralBuilder setCollateralPortfolio(List<? extends ReferenceWithMetaCollateralPortfolio> collateralPortfolio5);
		Collateral.CollateralBuilder addCollateralPortfolioValue(List<? extends CollateralPortfolio> collateralPortfolio6);
		Collateral.CollateralBuilder setCollateralPortfolioValue(List<? extends CollateralPortfolio> collateralPortfolio7);
		Collateral.CollateralBuilder setCollateralProvisions(CollateralProvisions collateralProvisions);
		Collateral.CollateralBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("independentAmount"), processor, IndependentAmount.IndependentAmountBuilder.class, getIndependentAmount());
			processRosetta(path.newSubPath("portfolioIdentifier"), processor, Identifier.IdentifierBuilder.class, getPortfolioIdentifier());
			processRosetta(path.newSubPath("collateralPortfolio"), processor, ReferenceWithMetaCollateralPortfolio.ReferenceWithMetaCollateralPortfolioBuilder.class, getCollateralPortfolio());
			processRosetta(path.newSubPath("collateralProvisions"), processor, CollateralProvisions.CollateralProvisionsBuilder.class, getCollateralProvisions());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		Collateral.CollateralBuilder prune();
	}

	/*********************** Immutable Implementation of Collateral  ***********************/
	class CollateralImpl implements Collateral {
		private final IndependentAmount independentAmount;
		private final List<? extends Identifier> portfolioIdentifier;
		private final List<? extends ReferenceWithMetaCollateralPortfolio> collateralPortfolio;
		private final CollateralProvisions collateralProvisions;
		private final MetaFields meta;
		
		protected CollateralImpl(Collateral.CollateralBuilder builder) {
			this.independentAmount = ofNullable(builder.getIndependentAmount()).map(f->f.build()).orElse(null);
			this.portfolioIdentifier = ofNullable(builder.getPortfolioIdentifier()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.collateralPortfolio = ofNullable(builder.getCollateralPortfolio()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.collateralProvisions = ofNullable(builder.getCollateralProvisions()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("independentAmount")
		public IndependentAmount getIndependentAmount() {
			return independentAmount;
		}
		
		@Override
		@RosettaAttribute("portfolioIdentifier")
		public List<? extends Identifier> getPortfolioIdentifier() {
			return portfolioIdentifier;
		}
		
		@Override
		@RosettaAttribute("collateralPortfolio")
		public List<? extends ReferenceWithMetaCollateralPortfolio> getCollateralPortfolio() {
			return collateralPortfolio;
		}
		
		@Override
		@RosettaAttribute("collateralProvisions")
		public CollateralProvisions getCollateralProvisions() {
			return collateralProvisions;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public Collateral build() {
			return this;
		}
		
		@Override
		public Collateral.CollateralBuilder toBuilder() {
			Collateral.CollateralBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Collateral.CollateralBuilder builder) {
			ofNullable(getIndependentAmount()).ifPresent(builder::setIndependentAmount);
			ofNullable(getPortfolioIdentifier()).ifPresent(builder::setPortfolioIdentifier);
			ofNullable(getCollateralPortfolio()).ifPresent(builder::setCollateralPortfolio);
			ofNullable(getCollateralProvisions()).ifPresent(builder::setCollateralProvisions);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Collateral _that = getType().cast(o);
		
			if (!Objects.equals(independentAmount, _that.getIndependentAmount())) return false;
			if (!ListEquals.listEquals(portfolioIdentifier, _that.getPortfolioIdentifier())) return false;
			if (!ListEquals.listEquals(collateralPortfolio, _that.getCollateralPortfolio())) return false;
			if (!Objects.equals(collateralProvisions, _that.getCollateralProvisions())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (independentAmount != null ? independentAmount.hashCode() : 0);
			_result = 31 * _result + (portfolioIdentifier != null ? portfolioIdentifier.hashCode() : 0);
			_result = 31 * _result + (collateralPortfolio != null ? collateralPortfolio.hashCode() : 0);
			_result = 31 * _result + (collateralProvisions != null ? collateralProvisions.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Collateral {" +
				"independentAmount=" + this.independentAmount + ", " +
				"portfolioIdentifier=" + this.portfolioIdentifier + ", " +
				"collateralPortfolio=" + this.collateralPortfolio + ", " +
				"collateralProvisions=" + this.collateralProvisions + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of Collateral  ***********************/
	class CollateralBuilderImpl implements Collateral.CollateralBuilder, GlobalKeyBuilder {
	
		protected IndependentAmount.IndependentAmountBuilder independentAmount;
		protected List<Identifier.IdentifierBuilder> portfolioIdentifier = new ArrayList<>();
		protected List<ReferenceWithMetaCollateralPortfolio.ReferenceWithMetaCollateralPortfolioBuilder> collateralPortfolio = new ArrayList<>();
		protected CollateralProvisions.CollateralProvisionsBuilder collateralProvisions;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public CollateralBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("independentAmount")
		public IndependentAmount.IndependentAmountBuilder getIndependentAmount() {
			return independentAmount;
		}
		
		@Override
		public IndependentAmount.IndependentAmountBuilder getOrCreateIndependentAmount() {
			IndependentAmount.IndependentAmountBuilder result;
			if (independentAmount!=null) {
				result = independentAmount;
			}
			else {
				result = independentAmount = IndependentAmount.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("portfolioIdentifier")
		public List<? extends Identifier.IdentifierBuilder> getPortfolioIdentifier() {
			return portfolioIdentifier;
		}
		
		public Identifier.IdentifierBuilder getOrCreatePortfolioIdentifier(int _index) {
		
			if (portfolioIdentifier==null) {
				this.portfolioIdentifier = new ArrayList<>();
			}
			Identifier.IdentifierBuilder result;
			return getIndex(portfolioIdentifier, _index, () -> {
						Identifier.IdentifierBuilder newPortfolioIdentifier = Identifier.builder();
						return newPortfolioIdentifier;
					});
		}
		
		@Override
		@RosettaAttribute("collateralPortfolio")
		public List<? extends ReferenceWithMetaCollateralPortfolio.ReferenceWithMetaCollateralPortfolioBuilder> getCollateralPortfolio() {
			return collateralPortfolio;
		}
		
		public ReferenceWithMetaCollateralPortfolio.ReferenceWithMetaCollateralPortfolioBuilder getOrCreateCollateralPortfolio(int _index) {
		
			if (collateralPortfolio==null) {
				this.collateralPortfolio = new ArrayList<>();
			}
			ReferenceWithMetaCollateralPortfolio.ReferenceWithMetaCollateralPortfolioBuilder result;
			return getIndex(collateralPortfolio, _index, () -> {
						ReferenceWithMetaCollateralPortfolio.ReferenceWithMetaCollateralPortfolioBuilder newCollateralPortfolio = ReferenceWithMetaCollateralPortfolio.builder();
						return newCollateralPortfolio;
					});
		}
		
		@Override
		@RosettaAttribute("collateralProvisions")
		public CollateralProvisions.CollateralProvisionsBuilder getCollateralProvisions() {
			return collateralProvisions;
		}
		
		@Override
		public CollateralProvisions.CollateralProvisionsBuilder getOrCreateCollateralProvisions() {
			CollateralProvisions.CollateralProvisionsBuilder result;
			if (collateralProvisions!=null) {
				result = collateralProvisions;
			}
			else {
				result = collateralProvisions = CollateralProvisions.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("meta")
		public MetaFields.MetaFieldsBuilder getMeta() {
			return meta;
		}
		
		@Override
		public MetaFields.MetaFieldsBuilder getOrCreateMeta() {
			MetaFields.MetaFieldsBuilder result;
			if (meta!=null) {
				result = meta;
			}
			else {
				result = meta = MetaFields.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("independentAmount")
		public Collateral.CollateralBuilder setIndependentAmount(IndependentAmount independentAmount) {
			this.independentAmount = independentAmount==null?null:independentAmount.toBuilder();
			return this;
		}
		@Override
		public Collateral.CollateralBuilder addPortfolioIdentifier(Identifier portfolioIdentifier) {
			if (portfolioIdentifier!=null) this.portfolioIdentifier.add(portfolioIdentifier.toBuilder());
			return this;
		}
		
		@Override
		public Collateral.CollateralBuilder addPortfolioIdentifier(Identifier portfolioIdentifier, int _idx) {
			getIndex(this.portfolioIdentifier, _idx, () -> portfolioIdentifier.toBuilder());
			return this;
		}
		@Override 
		public Collateral.CollateralBuilder addPortfolioIdentifier(List<? extends Identifier> portfolioIdentifiers) {
			if (portfolioIdentifiers != null) {
				for (Identifier toAdd : portfolioIdentifiers) {
					this.portfolioIdentifier.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("portfolioIdentifier")
		public Collateral.CollateralBuilder setPortfolioIdentifier(List<? extends Identifier> portfolioIdentifiers) {
			if (portfolioIdentifiers == null)  {
				this.portfolioIdentifier = new ArrayList<>();
			}
			else {
				this.portfolioIdentifier = portfolioIdentifiers.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public Collateral.CollateralBuilder addCollateralPortfolio(ReferenceWithMetaCollateralPortfolio collateralPortfolio) {
			if (collateralPortfolio!=null) this.collateralPortfolio.add(collateralPortfolio.toBuilder());
			return this;
		}
		
		@Override
		public Collateral.CollateralBuilder addCollateralPortfolio(ReferenceWithMetaCollateralPortfolio collateralPortfolio, int _idx) {
			getIndex(this.collateralPortfolio, _idx, () -> collateralPortfolio.toBuilder());
			return this;
		}
		
		@Override
		public Collateral.CollateralBuilder addCollateralPortfolioValue(CollateralPortfolio collateralPortfolio) {
			this.getOrCreateCollateralPortfolio(-1).setValue(collateralPortfolio.toBuilder());
			return this;
		}
		
		@Override
		public Collateral.CollateralBuilder addCollateralPortfolioValue(CollateralPortfolio collateralPortfolio, int _idx) {
			this.getOrCreateCollateralPortfolio(_idx).setValue(collateralPortfolio.toBuilder());
			return this;
		}
		@Override 
		public Collateral.CollateralBuilder addCollateralPortfolio(List<? extends ReferenceWithMetaCollateralPortfolio> collateralPortfolios) {
			if (collateralPortfolios != null) {
				for (ReferenceWithMetaCollateralPortfolio toAdd : collateralPortfolios) {
					this.collateralPortfolio.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("collateralPortfolio")
		public Collateral.CollateralBuilder setCollateralPortfolio(List<? extends ReferenceWithMetaCollateralPortfolio> collateralPortfolios) {
			if (collateralPortfolios == null)  {
				this.collateralPortfolio = new ArrayList<>();
			}
			else {
				this.collateralPortfolio = collateralPortfolios.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public Collateral.CollateralBuilder addCollateralPortfolioValue(List<? extends CollateralPortfolio> collateralPortfolios) {
			if (collateralPortfolios != null) {
				for (CollateralPortfolio toAdd : collateralPortfolios) {
					this.addCollateralPortfolioValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public Collateral.CollateralBuilder setCollateralPortfolioValue(List<? extends CollateralPortfolio> collateralPortfolios) {
			this.collateralPortfolio.clear();
			if (collateralPortfolios!=null) {
				collateralPortfolios.forEach(this::addCollateralPortfolioValue);
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("collateralProvisions")
		public Collateral.CollateralBuilder setCollateralProvisions(CollateralProvisions collateralProvisions) {
			this.collateralProvisions = collateralProvisions==null?null:collateralProvisions.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public Collateral.CollateralBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public Collateral build() {
			return new Collateral.CollateralImpl(this);
		}
		
		@Override
		public Collateral.CollateralBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Collateral.CollateralBuilder prune() {
			if (independentAmount!=null && !independentAmount.prune().hasData()) independentAmount = null;
			portfolioIdentifier = portfolioIdentifier.stream().filter(b->b!=null).<Identifier.IdentifierBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			collateralPortfolio = collateralPortfolio.stream().filter(b->b!=null).<ReferenceWithMetaCollateralPortfolio.ReferenceWithMetaCollateralPortfolioBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (collateralProvisions!=null && !collateralProvisions.prune().hasData()) collateralProvisions = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getIndependentAmount()!=null && getIndependentAmount().hasData()) return true;
			if (getPortfolioIdentifier()!=null && getPortfolioIdentifier().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getCollateralPortfolio()!=null && getCollateralPortfolio().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getCollateralProvisions()!=null && getCollateralProvisions().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Collateral.CollateralBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Collateral.CollateralBuilder o = (Collateral.CollateralBuilder) other;
			
			merger.mergeRosetta(getIndependentAmount(), o.getIndependentAmount(), this::setIndependentAmount);
			merger.mergeRosetta(getPortfolioIdentifier(), o.getPortfolioIdentifier(), this::getOrCreatePortfolioIdentifier);
			merger.mergeRosetta(getCollateralPortfolio(), o.getCollateralPortfolio(), this::getOrCreateCollateralPortfolio);
			merger.mergeRosetta(getCollateralProvisions(), o.getCollateralProvisions(), this::setCollateralProvisions);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Collateral _that = getType().cast(o);
		
			if (!Objects.equals(independentAmount, _that.getIndependentAmount())) return false;
			if (!ListEquals.listEquals(portfolioIdentifier, _that.getPortfolioIdentifier())) return false;
			if (!ListEquals.listEquals(collateralPortfolio, _that.getCollateralPortfolio())) return false;
			if (!Objects.equals(collateralProvisions, _that.getCollateralProvisions())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (independentAmount != null ? independentAmount.hashCode() : 0);
			_result = 31 * _result + (portfolioIdentifier != null ? portfolioIdentifier.hashCode() : 0);
			_result = 31 * _result + (collateralPortfolio != null ? collateralPortfolio.hashCode() : 0);
			_result = 31 * _result + (collateralProvisions != null ? collateralProvisions.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralBuilder {" +
				"independentAmount=" + this.independentAmount + ", " +
				"portfolioIdentifier=" + this.portfolioIdentifier + ", " +
				"collateralPortfolio=" + this.collateralPortfolio + ", " +
				"collateralProvisions=" + this.collateralProvisions + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
