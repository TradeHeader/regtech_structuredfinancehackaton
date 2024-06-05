package cdm.event.common;

import cdm.base.staticdata.identifier.Identifier;
import cdm.event.common.CollateralBalance;
import cdm.event.common.CollateralPortfolio;
import cdm.event.common.CollateralPortfolio.CollateralPortfolioBuilder;
import cdm.event.common.CollateralPortfolio.CollateralPortfolioBuilderImpl;
import cdm.event.common.CollateralPortfolio.CollateralPortfolioImpl;
import cdm.event.common.CollateralPosition;
import cdm.event.common.meta.CollateralPortfolioMeta;
import cdm.legaldocumentation.common.LegalAgreement;
import cdm.legaldocumentation.common.metafields.ReferenceWithMetaLegalAgreement;
import cdm.legaldocumentation.common.metafields.ReferenceWithMetaLegalAgreement.ReferenceWithMetaLegalAgreementBuilder;
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
 * Represents common attributes to define the details of collateral assets, to be used in margin call messaging and contribute to collateral balances e.g securities in a collateral account.
 * @version ${project.version}
 */
@RosettaDataType(value="CollateralPortfolio", builder=CollateralPortfolio.CollateralPortfolioBuilderImpl.class, version="${project.version}")
public interface CollateralPortfolio extends RosettaModelObject, GlobalKey {

	CollateralPortfolioMeta metaData = new CollateralPortfolioMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies a unique identifier for a set of collateral positions in a portfolio.
	 */
	Identifier getPortfolioIdentifier();
	/**
	 * Specifies the individual components of the collateral positions in the collateral portfolio.
	 */
	List<? extends CollateralPosition> getCollateralPosition();
	/**
	 * Represents the populated or calculated collateral aggregate balance amount for the collateral portfolio.
	 */
	List<? extends CollateralBalance> getCollateralBalance();
	/**
	 *  The specification of a legal agreement between two parties governing the collateral relationship such as Credit Support Agreement or Collateral Transfer Agreement etc. (NB: this can be provided by reference to a global key for each LegalAgreement object).
	 */
	ReferenceWithMetaLegalAgreement getLegalAgreement();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	CollateralPortfolio build();
	
	CollateralPortfolio.CollateralPortfolioBuilder toBuilder();
	
	static CollateralPortfolio.CollateralPortfolioBuilder builder() {
		return new CollateralPortfolio.CollateralPortfolioBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CollateralPortfolio> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CollateralPortfolio> getType() {
		return CollateralPortfolio.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("portfolioIdentifier"), processor, Identifier.class, getPortfolioIdentifier());
		processRosetta(path.newSubPath("collateralPosition"), processor, CollateralPosition.class, getCollateralPosition());
		processRosetta(path.newSubPath("collateralBalance"), processor, CollateralBalance.class, getCollateralBalance());
		processRosetta(path.newSubPath("legalAgreement"), processor, ReferenceWithMetaLegalAgreement.class, getLegalAgreement());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CollateralPortfolioBuilder extends CollateralPortfolio, RosettaModelObjectBuilder {
		Identifier.IdentifierBuilder getOrCreatePortfolioIdentifier();
		Identifier.IdentifierBuilder getPortfolioIdentifier();
		CollateralPosition.CollateralPositionBuilder getOrCreateCollateralPosition(int _index);
		List<? extends CollateralPosition.CollateralPositionBuilder> getCollateralPosition();
		CollateralBalance.CollateralBalanceBuilder getOrCreateCollateralBalance(int _index);
		List<? extends CollateralBalance.CollateralBalanceBuilder> getCollateralBalance();
		ReferenceWithMetaLegalAgreement.ReferenceWithMetaLegalAgreementBuilder getOrCreateLegalAgreement();
		ReferenceWithMetaLegalAgreement.ReferenceWithMetaLegalAgreementBuilder getLegalAgreement();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		CollateralPortfolio.CollateralPortfolioBuilder setPortfolioIdentifier(Identifier portfolioIdentifier);
		CollateralPortfolio.CollateralPortfolioBuilder addCollateralPosition(CollateralPosition collateralPosition0);
		CollateralPortfolio.CollateralPortfolioBuilder addCollateralPosition(CollateralPosition collateralPosition1, int _idx);
		CollateralPortfolio.CollateralPortfolioBuilder addCollateralPosition(List<? extends CollateralPosition> collateralPosition2);
		CollateralPortfolio.CollateralPortfolioBuilder setCollateralPosition(List<? extends CollateralPosition> collateralPosition3);
		CollateralPortfolio.CollateralPortfolioBuilder addCollateralBalance(CollateralBalance collateralBalance0);
		CollateralPortfolio.CollateralPortfolioBuilder addCollateralBalance(CollateralBalance collateralBalance1, int _idx);
		CollateralPortfolio.CollateralPortfolioBuilder addCollateralBalance(List<? extends CollateralBalance> collateralBalance2);
		CollateralPortfolio.CollateralPortfolioBuilder setCollateralBalance(List<? extends CollateralBalance> collateralBalance3);
		CollateralPortfolio.CollateralPortfolioBuilder setLegalAgreement(ReferenceWithMetaLegalAgreement legalAgreement0);
		CollateralPortfolio.CollateralPortfolioBuilder setLegalAgreementValue(LegalAgreement legalAgreement1);
		CollateralPortfolio.CollateralPortfolioBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("portfolioIdentifier"), processor, Identifier.IdentifierBuilder.class, getPortfolioIdentifier());
			processRosetta(path.newSubPath("collateralPosition"), processor, CollateralPosition.CollateralPositionBuilder.class, getCollateralPosition());
			processRosetta(path.newSubPath("collateralBalance"), processor, CollateralBalance.CollateralBalanceBuilder.class, getCollateralBalance());
			processRosetta(path.newSubPath("legalAgreement"), processor, ReferenceWithMetaLegalAgreement.ReferenceWithMetaLegalAgreementBuilder.class, getLegalAgreement());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		CollateralPortfolio.CollateralPortfolioBuilder prune();
	}

	/*********************** Immutable Implementation of CollateralPortfolio  ***********************/
	class CollateralPortfolioImpl implements CollateralPortfolio {
		private final Identifier portfolioIdentifier;
		private final List<? extends CollateralPosition> collateralPosition;
		private final List<? extends CollateralBalance> collateralBalance;
		private final ReferenceWithMetaLegalAgreement legalAgreement;
		private final MetaFields meta;
		
		protected CollateralPortfolioImpl(CollateralPortfolio.CollateralPortfolioBuilder builder) {
			this.portfolioIdentifier = ofNullable(builder.getPortfolioIdentifier()).map(f->f.build()).orElse(null);
			this.collateralPosition = ofNullable(builder.getCollateralPosition()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.collateralBalance = ofNullable(builder.getCollateralBalance()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.legalAgreement = ofNullable(builder.getLegalAgreement()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("portfolioIdentifier")
		public Identifier getPortfolioIdentifier() {
			return portfolioIdentifier;
		}
		
		@Override
		@RosettaAttribute("collateralPosition")
		public List<? extends CollateralPosition> getCollateralPosition() {
			return collateralPosition;
		}
		
		@Override
		@RosettaAttribute("collateralBalance")
		public List<? extends CollateralBalance> getCollateralBalance() {
			return collateralBalance;
		}
		
		@Override
		@RosettaAttribute("legalAgreement")
		public ReferenceWithMetaLegalAgreement getLegalAgreement() {
			return legalAgreement;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public CollateralPortfolio build() {
			return this;
		}
		
		@Override
		public CollateralPortfolio.CollateralPortfolioBuilder toBuilder() {
			CollateralPortfolio.CollateralPortfolioBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CollateralPortfolio.CollateralPortfolioBuilder builder) {
			ofNullable(getPortfolioIdentifier()).ifPresent(builder::setPortfolioIdentifier);
			ofNullable(getCollateralPosition()).ifPresent(builder::setCollateralPosition);
			ofNullable(getCollateralBalance()).ifPresent(builder::setCollateralBalance);
			ofNullable(getLegalAgreement()).ifPresent(builder::setLegalAgreement);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralPortfolio _that = getType().cast(o);
		
			if (!Objects.equals(portfolioIdentifier, _that.getPortfolioIdentifier())) return false;
			if (!ListEquals.listEquals(collateralPosition, _that.getCollateralPosition())) return false;
			if (!ListEquals.listEquals(collateralBalance, _that.getCollateralBalance())) return false;
			if (!Objects.equals(legalAgreement, _that.getLegalAgreement())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (portfolioIdentifier != null ? portfolioIdentifier.hashCode() : 0);
			_result = 31 * _result + (collateralPosition != null ? collateralPosition.hashCode() : 0);
			_result = 31 * _result + (collateralBalance != null ? collateralBalance.hashCode() : 0);
			_result = 31 * _result + (legalAgreement != null ? legalAgreement.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralPortfolio {" +
				"portfolioIdentifier=" + this.portfolioIdentifier + ", " +
				"collateralPosition=" + this.collateralPosition + ", " +
				"collateralBalance=" + this.collateralBalance + ", " +
				"legalAgreement=" + this.legalAgreement + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of CollateralPortfolio  ***********************/
	class CollateralPortfolioBuilderImpl implements CollateralPortfolio.CollateralPortfolioBuilder, GlobalKeyBuilder {
	
		protected Identifier.IdentifierBuilder portfolioIdentifier;
		protected List<CollateralPosition.CollateralPositionBuilder> collateralPosition = new ArrayList<>();
		protected List<CollateralBalance.CollateralBalanceBuilder> collateralBalance = new ArrayList<>();
		protected ReferenceWithMetaLegalAgreement.ReferenceWithMetaLegalAgreementBuilder legalAgreement;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public CollateralPortfolioBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("portfolioIdentifier")
		public Identifier.IdentifierBuilder getPortfolioIdentifier() {
			return portfolioIdentifier;
		}
		
		@Override
		public Identifier.IdentifierBuilder getOrCreatePortfolioIdentifier() {
			Identifier.IdentifierBuilder result;
			if (portfolioIdentifier!=null) {
				result = portfolioIdentifier;
			}
			else {
				result = portfolioIdentifier = Identifier.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("collateralPosition")
		public List<? extends CollateralPosition.CollateralPositionBuilder> getCollateralPosition() {
			return collateralPosition;
		}
		
		public CollateralPosition.CollateralPositionBuilder getOrCreateCollateralPosition(int _index) {
		
			if (collateralPosition==null) {
				this.collateralPosition = new ArrayList<>();
			}
			CollateralPosition.CollateralPositionBuilder result;
			return getIndex(collateralPosition, _index, () -> {
						CollateralPosition.CollateralPositionBuilder newCollateralPosition = CollateralPosition.builder();
						return newCollateralPosition;
					});
		}
		
		@Override
		@RosettaAttribute("collateralBalance")
		public List<? extends CollateralBalance.CollateralBalanceBuilder> getCollateralBalance() {
			return collateralBalance;
		}
		
		public CollateralBalance.CollateralBalanceBuilder getOrCreateCollateralBalance(int _index) {
		
			if (collateralBalance==null) {
				this.collateralBalance = new ArrayList<>();
			}
			CollateralBalance.CollateralBalanceBuilder result;
			return getIndex(collateralBalance, _index, () -> {
						CollateralBalance.CollateralBalanceBuilder newCollateralBalance = CollateralBalance.builder();
						return newCollateralBalance;
					});
		}
		
		@Override
		@RosettaAttribute("legalAgreement")
		public ReferenceWithMetaLegalAgreement.ReferenceWithMetaLegalAgreementBuilder getLegalAgreement() {
			return legalAgreement;
		}
		
		@Override
		public ReferenceWithMetaLegalAgreement.ReferenceWithMetaLegalAgreementBuilder getOrCreateLegalAgreement() {
			ReferenceWithMetaLegalAgreement.ReferenceWithMetaLegalAgreementBuilder result;
			if (legalAgreement!=null) {
				result = legalAgreement;
			}
			else {
				result = legalAgreement = ReferenceWithMetaLegalAgreement.builder();
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
		@RosettaAttribute("portfolioIdentifier")
		public CollateralPortfolio.CollateralPortfolioBuilder setPortfolioIdentifier(Identifier portfolioIdentifier) {
			this.portfolioIdentifier = portfolioIdentifier==null?null:portfolioIdentifier.toBuilder();
			return this;
		}
		@Override
		public CollateralPortfolio.CollateralPortfolioBuilder addCollateralPosition(CollateralPosition collateralPosition) {
			if (collateralPosition!=null) this.collateralPosition.add(collateralPosition.toBuilder());
			return this;
		}
		
		@Override
		public CollateralPortfolio.CollateralPortfolioBuilder addCollateralPosition(CollateralPosition collateralPosition, int _idx) {
			getIndex(this.collateralPosition, _idx, () -> collateralPosition.toBuilder());
			return this;
		}
		@Override 
		public CollateralPortfolio.CollateralPortfolioBuilder addCollateralPosition(List<? extends CollateralPosition> collateralPositions) {
			if (collateralPositions != null) {
				for (CollateralPosition toAdd : collateralPositions) {
					this.collateralPosition.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("collateralPosition")
		public CollateralPortfolio.CollateralPortfolioBuilder setCollateralPosition(List<? extends CollateralPosition> collateralPositions) {
			if (collateralPositions == null)  {
				this.collateralPosition = new ArrayList<>();
			}
			else {
				this.collateralPosition = collateralPositions.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public CollateralPortfolio.CollateralPortfolioBuilder addCollateralBalance(CollateralBalance collateralBalance) {
			if (collateralBalance!=null) this.collateralBalance.add(collateralBalance.toBuilder());
			return this;
		}
		
		@Override
		public CollateralPortfolio.CollateralPortfolioBuilder addCollateralBalance(CollateralBalance collateralBalance, int _idx) {
			getIndex(this.collateralBalance, _idx, () -> collateralBalance.toBuilder());
			return this;
		}
		@Override 
		public CollateralPortfolio.CollateralPortfolioBuilder addCollateralBalance(List<? extends CollateralBalance> collateralBalances) {
			if (collateralBalances != null) {
				for (CollateralBalance toAdd : collateralBalances) {
					this.collateralBalance.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("collateralBalance")
		public CollateralPortfolio.CollateralPortfolioBuilder setCollateralBalance(List<? extends CollateralBalance> collateralBalances) {
			if (collateralBalances == null)  {
				this.collateralBalance = new ArrayList<>();
			}
			else {
				this.collateralBalance = collateralBalances.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("legalAgreement")
		public CollateralPortfolio.CollateralPortfolioBuilder setLegalAgreement(ReferenceWithMetaLegalAgreement legalAgreement) {
			this.legalAgreement = legalAgreement==null?null:legalAgreement.toBuilder();
			return this;
		}
		@Override
		public CollateralPortfolio.CollateralPortfolioBuilder setLegalAgreementValue(LegalAgreement legalAgreement) {
			this.getOrCreateLegalAgreement().setValue(legalAgreement);
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public CollateralPortfolio.CollateralPortfolioBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public CollateralPortfolio build() {
			return new CollateralPortfolio.CollateralPortfolioImpl(this);
		}
		
		@Override
		public CollateralPortfolio.CollateralPortfolioBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralPortfolio.CollateralPortfolioBuilder prune() {
			if (portfolioIdentifier!=null && !portfolioIdentifier.prune().hasData()) portfolioIdentifier = null;
			collateralPosition = collateralPosition.stream().filter(b->b!=null).<CollateralPosition.CollateralPositionBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			collateralBalance = collateralBalance.stream().filter(b->b!=null).<CollateralBalance.CollateralBalanceBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (legalAgreement!=null && !legalAgreement.prune().hasData()) legalAgreement = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPortfolioIdentifier()!=null && getPortfolioIdentifier().hasData()) return true;
			if (getCollateralPosition()!=null && getCollateralPosition().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getCollateralBalance()!=null && getCollateralBalance().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getLegalAgreement()!=null && getLegalAgreement().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralPortfolio.CollateralPortfolioBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CollateralPortfolio.CollateralPortfolioBuilder o = (CollateralPortfolio.CollateralPortfolioBuilder) other;
			
			merger.mergeRosetta(getPortfolioIdentifier(), o.getPortfolioIdentifier(), this::setPortfolioIdentifier);
			merger.mergeRosetta(getCollateralPosition(), o.getCollateralPosition(), this::getOrCreateCollateralPosition);
			merger.mergeRosetta(getCollateralBalance(), o.getCollateralBalance(), this::getOrCreateCollateralBalance);
			merger.mergeRosetta(getLegalAgreement(), o.getLegalAgreement(), this::setLegalAgreement);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralPortfolio _that = getType().cast(o);
		
			if (!Objects.equals(portfolioIdentifier, _that.getPortfolioIdentifier())) return false;
			if (!ListEquals.listEquals(collateralPosition, _that.getCollateralPosition())) return false;
			if (!ListEquals.listEquals(collateralBalance, _that.getCollateralBalance())) return false;
			if (!Objects.equals(legalAgreement, _that.getLegalAgreement())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (portfolioIdentifier != null ? portfolioIdentifier.hashCode() : 0);
			_result = 31 * _result + (collateralPosition != null ? collateralPosition.hashCode() : 0);
			_result = 31 * _result + (collateralBalance != null ? collateralBalance.hashCode() : 0);
			_result = 31 * _result + (legalAgreement != null ? legalAgreement.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralPortfolioBuilder {" +
				"portfolioIdentifier=" + this.portfolioIdentifier + ", " +
				"collateralPosition=" + this.collateralPosition + ", " +
				"collateralBalance=" + this.collateralBalance + ", " +
				"legalAgreement=" + this.legalAgreement + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
