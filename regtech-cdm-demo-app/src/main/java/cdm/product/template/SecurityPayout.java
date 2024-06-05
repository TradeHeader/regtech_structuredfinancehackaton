package cdm.product.template;

import cdm.observable.asset.SecurityValuation;
import cdm.product.template.InitialMargin;
import cdm.product.template.RepoDurationEnum;
import cdm.product.template.SecurityLeg;
import cdm.product.template.SecurityPayout;
import cdm.product.template.SecurityPayout.SecurityPayoutBuilder;
import cdm.product.template.SecurityPayout.SecurityPayoutBuilderImpl;
import cdm.product.template.SecurityPayout.SecurityPayoutImpl;
import cdm.product.template.meta.SecurityPayoutMeta;
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
 *  Security payout specification in case the product payout involves some form of security collateral, as in a securities financing transaction.
 * @version ${project.version}
 */
@RosettaDataType(value="SecurityPayout", builder=SecurityPayout.SecurityPayoutBuilderImpl.class, version="${project.version}")
public interface SecurityPayout extends RosettaModelObject, GlobalKey {

	SecurityPayoutMeta metaData = new SecurityPayoutMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Each SecurityLeg represent a buy/sell at different dates, typically 1 near leg and 1 far leg in a securities financing transaction.
	 */
	List<? extends SecurityLeg> getSecurityLeg();
	/**
	 * RepoDurationEnum.
	 */
	InitialMargin getInitialMargin();
	/**
	 * A duration code for the repo transaction. This defines a type of a repo transaction with fixed duration.
	 */
	RepoDurationEnum getRepoDuration();
	/**
	 * The underlying securities and their valuation for the security leg.
	 */
	List<? extends SecurityValuation> getSecurityValuation();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	SecurityPayout build();
	
	SecurityPayout.SecurityPayoutBuilder toBuilder();
	
	static SecurityPayout.SecurityPayoutBuilder builder() {
		return new SecurityPayout.SecurityPayoutBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends SecurityPayout> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends SecurityPayout> getType() {
		return SecurityPayout.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("securityLeg"), processor, SecurityLeg.class, getSecurityLeg());
		processRosetta(path.newSubPath("initialMargin"), processor, InitialMargin.class, getInitialMargin());
		processor.processBasic(path.newSubPath("repoDuration"), RepoDurationEnum.class, getRepoDuration(), this);
		processRosetta(path.newSubPath("securityValuation"), processor, SecurityValuation.class, getSecurityValuation());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface SecurityPayoutBuilder extends SecurityPayout, RosettaModelObjectBuilder {
		SecurityLeg.SecurityLegBuilder getOrCreateSecurityLeg(int _index);
		List<? extends SecurityLeg.SecurityLegBuilder> getSecurityLeg();
		InitialMargin.InitialMarginBuilder getOrCreateInitialMargin();
		InitialMargin.InitialMarginBuilder getInitialMargin();
		SecurityValuation.SecurityValuationBuilder getOrCreateSecurityValuation(int _index);
		List<? extends SecurityValuation.SecurityValuationBuilder> getSecurityValuation();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		SecurityPayout.SecurityPayoutBuilder addSecurityLeg(SecurityLeg securityLeg0);
		SecurityPayout.SecurityPayoutBuilder addSecurityLeg(SecurityLeg securityLeg1, int _idx);
		SecurityPayout.SecurityPayoutBuilder addSecurityLeg(List<? extends SecurityLeg> securityLeg2);
		SecurityPayout.SecurityPayoutBuilder setSecurityLeg(List<? extends SecurityLeg> securityLeg3);
		SecurityPayout.SecurityPayoutBuilder setInitialMargin(InitialMargin initialMargin);
		SecurityPayout.SecurityPayoutBuilder setRepoDuration(RepoDurationEnum repoDuration);
		SecurityPayout.SecurityPayoutBuilder addSecurityValuation(SecurityValuation securityValuation0);
		SecurityPayout.SecurityPayoutBuilder addSecurityValuation(SecurityValuation securityValuation1, int _idx);
		SecurityPayout.SecurityPayoutBuilder addSecurityValuation(List<? extends SecurityValuation> securityValuation2);
		SecurityPayout.SecurityPayoutBuilder setSecurityValuation(List<? extends SecurityValuation> securityValuation3);
		SecurityPayout.SecurityPayoutBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("securityLeg"), processor, SecurityLeg.SecurityLegBuilder.class, getSecurityLeg());
			processRosetta(path.newSubPath("initialMargin"), processor, InitialMargin.InitialMarginBuilder.class, getInitialMargin());
			processor.processBasic(path.newSubPath("repoDuration"), RepoDurationEnum.class, getRepoDuration(), this);
			processRosetta(path.newSubPath("securityValuation"), processor, SecurityValuation.SecurityValuationBuilder.class, getSecurityValuation());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		SecurityPayout.SecurityPayoutBuilder prune();
	}

	/*********************** Immutable Implementation of SecurityPayout  ***********************/
	class SecurityPayoutImpl implements SecurityPayout {
		private final List<? extends SecurityLeg> securityLeg;
		private final InitialMargin initialMargin;
		private final RepoDurationEnum repoDuration;
		private final List<? extends SecurityValuation> securityValuation;
		private final MetaFields meta;
		
		protected SecurityPayoutImpl(SecurityPayout.SecurityPayoutBuilder builder) {
			this.securityLeg = ofNullable(builder.getSecurityLeg()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.initialMargin = ofNullable(builder.getInitialMargin()).map(f->f.build()).orElse(null);
			this.repoDuration = builder.getRepoDuration();
			this.securityValuation = ofNullable(builder.getSecurityValuation()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("securityLeg")
		public List<? extends SecurityLeg> getSecurityLeg() {
			return securityLeg;
		}
		
		@Override
		@RosettaAttribute("initialMargin")
		public InitialMargin getInitialMargin() {
			return initialMargin;
		}
		
		@Override
		@RosettaAttribute("repoDuration")
		public RepoDurationEnum getRepoDuration() {
			return repoDuration;
		}
		
		@Override
		@RosettaAttribute("securityValuation")
		public List<? extends SecurityValuation> getSecurityValuation() {
			return securityValuation;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public SecurityPayout build() {
			return this;
		}
		
		@Override
		public SecurityPayout.SecurityPayoutBuilder toBuilder() {
			SecurityPayout.SecurityPayoutBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(SecurityPayout.SecurityPayoutBuilder builder) {
			ofNullable(getSecurityLeg()).ifPresent(builder::setSecurityLeg);
			ofNullable(getInitialMargin()).ifPresent(builder::setInitialMargin);
			ofNullable(getRepoDuration()).ifPresent(builder::setRepoDuration);
			ofNullable(getSecurityValuation()).ifPresent(builder::setSecurityValuation);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SecurityPayout _that = getType().cast(o);
		
			if (!ListEquals.listEquals(securityLeg, _that.getSecurityLeg())) return false;
			if (!Objects.equals(initialMargin, _that.getInitialMargin())) return false;
			if (!Objects.equals(repoDuration, _that.getRepoDuration())) return false;
			if (!ListEquals.listEquals(securityValuation, _that.getSecurityValuation())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (securityLeg != null ? securityLeg.hashCode() : 0);
			_result = 31 * _result + (initialMargin != null ? initialMargin.hashCode() : 0);
			_result = 31 * _result + (repoDuration != null ? repoDuration.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (securityValuation != null ? securityValuation.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SecurityPayout {" +
				"securityLeg=" + this.securityLeg + ", " +
				"initialMargin=" + this.initialMargin + ", " +
				"repoDuration=" + this.repoDuration + ", " +
				"securityValuation=" + this.securityValuation + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of SecurityPayout  ***********************/
	class SecurityPayoutBuilderImpl implements SecurityPayout.SecurityPayoutBuilder, GlobalKeyBuilder {
	
		protected List<SecurityLeg.SecurityLegBuilder> securityLeg = new ArrayList<>();
		protected InitialMargin.InitialMarginBuilder initialMargin;
		protected RepoDurationEnum repoDuration;
		protected List<SecurityValuation.SecurityValuationBuilder> securityValuation = new ArrayList<>();
		protected MetaFields.MetaFieldsBuilder meta;
	
		public SecurityPayoutBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("securityLeg")
		public List<? extends SecurityLeg.SecurityLegBuilder> getSecurityLeg() {
			return securityLeg;
		}
		
		public SecurityLeg.SecurityLegBuilder getOrCreateSecurityLeg(int _index) {
		
			if (securityLeg==null) {
				this.securityLeg = new ArrayList<>();
			}
			SecurityLeg.SecurityLegBuilder result;
			return getIndex(securityLeg, _index, () -> {
						SecurityLeg.SecurityLegBuilder newSecurityLeg = SecurityLeg.builder();
						return newSecurityLeg;
					});
		}
		
		@Override
		@RosettaAttribute("initialMargin")
		public InitialMargin.InitialMarginBuilder getInitialMargin() {
			return initialMargin;
		}
		
		@Override
		public InitialMargin.InitialMarginBuilder getOrCreateInitialMargin() {
			InitialMargin.InitialMarginBuilder result;
			if (initialMargin!=null) {
				result = initialMargin;
			}
			else {
				result = initialMargin = InitialMargin.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("repoDuration")
		public RepoDurationEnum getRepoDuration() {
			return repoDuration;
		}
		
		@Override
		@RosettaAttribute("securityValuation")
		public List<? extends SecurityValuation.SecurityValuationBuilder> getSecurityValuation() {
			return securityValuation;
		}
		
		public SecurityValuation.SecurityValuationBuilder getOrCreateSecurityValuation(int _index) {
		
			if (securityValuation==null) {
				this.securityValuation = new ArrayList<>();
			}
			SecurityValuation.SecurityValuationBuilder result;
			return getIndex(securityValuation, _index, () -> {
						SecurityValuation.SecurityValuationBuilder newSecurityValuation = SecurityValuation.builder();
						return newSecurityValuation;
					});
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
		public SecurityPayout.SecurityPayoutBuilder addSecurityLeg(SecurityLeg securityLeg) {
			if (securityLeg!=null) this.securityLeg.add(securityLeg.toBuilder());
			return this;
		}
		
		@Override
		public SecurityPayout.SecurityPayoutBuilder addSecurityLeg(SecurityLeg securityLeg, int _idx) {
			getIndex(this.securityLeg, _idx, () -> securityLeg.toBuilder());
			return this;
		}
		@Override 
		public SecurityPayout.SecurityPayoutBuilder addSecurityLeg(List<? extends SecurityLeg> securityLegs) {
			if (securityLegs != null) {
				for (SecurityLeg toAdd : securityLegs) {
					this.securityLeg.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("securityLeg")
		public SecurityPayout.SecurityPayoutBuilder setSecurityLeg(List<? extends SecurityLeg> securityLegs) {
			if (securityLegs == null)  {
				this.securityLeg = new ArrayList<>();
			}
			else {
				this.securityLeg = securityLegs.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("initialMargin")
		public SecurityPayout.SecurityPayoutBuilder setInitialMargin(InitialMargin initialMargin) {
			this.initialMargin = initialMargin==null?null:initialMargin.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("repoDuration")
		public SecurityPayout.SecurityPayoutBuilder setRepoDuration(RepoDurationEnum repoDuration) {
			this.repoDuration = repoDuration==null?null:repoDuration;
			return this;
		}
		@Override
		public SecurityPayout.SecurityPayoutBuilder addSecurityValuation(SecurityValuation securityValuation) {
			if (securityValuation!=null) this.securityValuation.add(securityValuation.toBuilder());
			return this;
		}
		
		@Override
		public SecurityPayout.SecurityPayoutBuilder addSecurityValuation(SecurityValuation securityValuation, int _idx) {
			getIndex(this.securityValuation, _idx, () -> securityValuation.toBuilder());
			return this;
		}
		@Override 
		public SecurityPayout.SecurityPayoutBuilder addSecurityValuation(List<? extends SecurityValuation> securityValuations) {
			if (securityValuations != null) {
				for (SecurityValuation toAdd : securityValuations) {
					this.securityValuation.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("securityValuation")
		public SecurityPayout.SecurityPayoutBuilder setSecurityValuation(List<? extends SecurityValuation> securityValuations) {
			if (securityValuations == null)  {
				this.securityValuation = new ArrayList<>();
			}
			else {
				this.securityValuation = securityValuations.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("meta")
		public SecurityPayout.SecurityPayoutBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public SecurityPayout build() {
			return new SecurityPayout.SecurityPayoutImpl(this);
		}
		
		@Override
		public SecurityPayout.SecurityPayoutBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SecurityPayout.SecurityPayoutBuilder prune() {
			securityLeg = securityLeg.stream().filter(b->b!=null).<SecurityLeg.SecurityLegBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (initialMargin!=null && !initialMargin.prune().hasData()) initialMargin = null;
			securityValuation = securityValuation.stream().filter(b->b!=null).<SecurityValuation.SecurityValuationBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getSecurityLeg()!=null && getSecurityLeg().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getInitialMargin()!=null && getInitialMargin().hasData()) return true;
			if (getRepoDuration()!=null) return true;
			if (getSecurityValuation()!=null && getSecurityValuation().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public SecurityPayout.SecurityPayoutBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			SecurityPayout.SecurityPayoutBuilder o = (SecurityPayout.SecurityPayoutBuilder) other;
			
			merger.mergeRosetta(getSecurityLeg(), o.getSecurityLeg(), this::getOrCreateSecurityLeg);
			merger.mergeRosetta(getInitialMargin(), o.getInitialMargin(), this::setInitialMargin);
			merger.mergeRosetta(getSecurityValuation(), o.getSecurityValuation(), this::getOrCreateSecurityValuation);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getRepoDuration(), o.getRepoDuration(), this::setRepoDuration);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			SecurityPayout _that = getType().cast(o);
		
			if (!ListEquals.listEquals(securityLeg, _that.getSecurityLeg())) return false;
			if (!Objects.equals(initialMargin, _that.getInitialMargin())) return false;
			if (!Objects.equals(repoDuration, _that.getRepoDuration())) return false;
			if (!ListEquals.listEquals(securityValuation, _that.getSecurityValuation())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (securityLeg != null ? securityLeg.hashCode() : 0);
			_result = 31 * _result + (initialMargin != null ? initialMargin.hashCode() : 0);
			_result = 31 * _result + (repoDuration != null ? repoDuration.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (securityValuation != null ? securityValuation.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "SecurityPayoutBuilder {" +
				"securityLeg=" + this.securityLeg + ", " +
				"initialMargin=" + this.initialMargin + ", " +
				"repoDuration=" + this.repoDuration + ", " +
				"securityValuation=" + this.securityValuation + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
