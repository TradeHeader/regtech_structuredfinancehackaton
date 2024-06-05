package cdm.event.workflow;

import cdm.event.workflow.CreditLimitTypeEnum;
import cdm.event.workflow.CreditLimitUtilisation;
import cdm.event.workflow.LimitApplicable;
import cdm.event.workflow.LimitApplicable.LimitApplicableBuilder;
import cdm.event.workflow.LimitApplicable.LimitApplicableBuilderImpl;
import cdm.event.workflow.LimitApplicable.LimitApplicableImpl;
import cdm.event.workflow.Velocity;
import cdm.event.workflow.meta.LimitApplicableMeta;
import cdm.event.workflow.metafields.FieldWithMetaCreditLimitTypeEnum;
import cdm.event.workflow.metafields.FieldWithMetaCreditLimitTypeEnum.FieldWithMetaCreditLimitTypeEnumBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * @version ${project.version}
 */
@RosettaDataType(value="LimitApplicable", builder=LimitApplicable.LimitApplicableBuilderImpl.class, version="${project.version}")
public interface LimitApplicable extends RosettaModelObject {

	LimitApplicableMeta metaData = new LimitApplicableMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Standard code to indicate which type of credit line is being referred to - i.e. IM, DV01, PV01, CS01, Notional, Clip Size, Notional, maximumOrderQuantity.
	 */
	FieldWithMetaCreditLimitTypeEnum getLimitType();
	/**
	 * This element is required in FpML, optional in CDM for the purpose of accommodating the CME data representation while making reference to the FpML one.
	 */
	Integer getClipSize();
	/**
	 * The limit utilised by all the cleared trades for the limit level and limit type. While the attribute is of type integer in FpML and the CME schema, it has been specified to be of type number in the CDM to take into consideration java size limits as well as for consistency purposes with the way most monetary amounts are expressed.
	 */
	BigDecimal getAmountUtilized();
	CreditLimitUtilisation getUtilization();
	/**
	 * The limit remaining for the limit level and limit type. This does not take into account any pending trades. While the attribute is of type integer in FpML and the CME schema, it has been specified to be of type number in the CDM to take into consideration java size limits as well as for consistency purposes with the way most monetary amounts are expressed.
	 */
	BigDecimal getAmountRemaining();
	/**
	 * The currency in which the applicable limit is denominated. The list of valid currencies is not presently positioned as an enumeration as part of the CDM because that scope is limited to the values specified by ISDA and FpML. As a result, implementers have to make reference to the relevant standard, such as the ISO 4217 standard for currency codes.
	 */
	FieldWithMetaString getCurrency();
	Velocity getVelocity();

	/*********************** Build Methods  ***********************/
	LimitApplicable build();
	
	LimitApplicable.LimitApplicableBuilder toBuilder();
	
	static LimitApplicable.LimitApplicableBuilder builder() {
		return new LimitApplicable.LimitApplicableBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends LimitApplicable> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends LimitApplicable> getType() {
		return LimitApplicable.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("limitType"), processor, FieldWithMetaCreditLimitTypeEnum.class, getLimitType());
		processor.processBasic(path.newSubPath("clipSize"), Integer.class, getClipSize(), this);
		processor.processBasic(path.newSubPath("amountUtilized"), BigDecimal.class, getAmountUtilized(), this);
		processRosetta(path.newSubPath("utilization"), processor, CreditLimitUtilisation.class, getUtilization());
		processor.processBasic(path.newSubPath("amountRemaining"), BigDecimal.class, getAmountRemaining(), this);
		processRosetta(path.newSubPath("currency"), processor, FieldWithMetaString.class, getCurrency());
		processRosetta(path.newSubPath("velocity"), processor, Velocity.class, getVelocity());
	}
	

	/*********************** Builder Interface  ***********************/
	interface LimitApplicableBuilder extends LimitApplicable, RosettaModelObjectBuilder {
		FieldWithMetaCreditLimitTypeEnum.FieldWithMetaCreditLimitTypeEnumBuilder getOrCreateLimitType();
		FieldWithMetaCreditLimitTypeEnum.FieldWithMetaCreditLimitTypeEnumBuilder getLimitType();
		CreditLimitUtilisation.CreditLimitUtilisationBuilder getOrCreateUtilization();
		CreditLimitUtilisation.CreditLimitUtilisationBuilder getUtilization();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateCurrency();
		FieldWithMetaString.FieldWithMetaStringBuilder getCurrency();
		Velocity.VelocityBuilder getOrCreateVelocity();
		Velocity.VelocityBuilder getVelocity();
		LimitApplicable.LimitApplicableBuilder setLimitType(FieldWithMetaCreditLimitTypeEnum limitType0);
		LimitApplicable.LimitApplicableBuilder setLimitTypeValue(CreditLimitTypeEnum limitType1);
		LimitApplicable.LimitApplicableBuilder setClipSize(Integer clipSize);
		LimitApplicable.LimitApplicableBuilder setAmountUtilized(BigDecimal amountUtilized);
		LimitApplicable.LimitApplicableBuilder setUtilization(CreditLimitUtilisation utilization);
		LimitApplicable.LimitApplicableBuilder setAmountRemaining(BigDecimal amountRemaining);
		LimitApplicable.LimitApplicableBuilder setCurrency(FieldWithMetaString currency0);
		LimitApplicable.LimitApplicableBuilder setCurrencyValue(String currency1);
		LimitApplicable.LimitApplicableBuilder setVelocity(Velocity velocity);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("limitType"), processor, FieldWithMetaCreditLimitTypeEnum.FieldWithMetaCreditLimitTypeEnumBuilder.class, getLimitType());
			processor.processBasic(path.newSubPath("clipSize"), Integer.class, getClipSize(), this);
			processor.processBasic(path.newSubPath("amountUtilized"), BigDecimal.class, getAmountUtilized(), this);
			processRosetta(path.newSubPath("utilization"), processor, CreditLimitUtilisation.CreditLimitUtilisationBuilder.class, getUtilization());
			processor.processBasic(path.newSubPath("amountRemaining"), BigDecimal.class, getAmountRemaining(), this);
			processRosetta(path.newSubPath("currency"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getCurrency());
			processRosetta(path.newSubPath("velocity"), processor, Velocity.VelocityBuilder.class, getVelocity());
		}
		

		LimitApplicable.LimitApplicableBuilder prune();
	}

	/*********************** Immutable Implementation of LimitApplicable  ***********************/
	class LimitApplicableImpl implements LimitApplicable {
		private final FieldWithMetaCreditLimitTypeEnum limitType;
		private final Integer clipSize;
		private final BigDecimal amountUtilized;
		private final CreditLimitUtilisation utilization;
		private final BigDecimal amountRemaining;
		private final FieldWithMetaString currency;
		private final Velocity velocity;
		
		protected LimitApplicableImpl(LimitApplicable.LimitApplicableBuilder builder) {
			this.limitType = ofNullable(builder.getLimitType()).map(f->f.build()).orElse(null);
			this.clipSize = builder.getClipSize();
			this.amountUtilized = builder.getAmountUtilized();
			this.utilization = ofNullable(builder.getUtilization()).map(f->f.build()).orElse(null);
			this.amountRemaining = builder.getAmountRemaining();
			this.currency = ofNullable(builder.getCurrency()).map(f->f.build()).orElse(null);
			this.velocity = ofNullable(builder.getVelocity()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("limitType")
		public FieldWithMetaCreditLimitTypeEnum getLimitType() {
			return limitType;
		}
		
		@Override
		@RosettaAttribute("clipSize")
		public Integer getClipSize() {
			return clipSize;
		}
		
		@Override
		@RosettaAttribute("amountUtilized")
		public BigDecimal getAmountUtilized() {
			return amountUtilized;
		}
		
		@Override
		@RosettaAttribute("utilization")
		public CreditLimitUtilisation getUtilization() {
			return utilization;
		}
		
		@Override
		@RosettaAttribute("amountRemaining")
		public BigDecimal getAmountRemaining() {
			return amountRemaining;
		}
		
		@Override
		@RosettaAttribute("currency")
		public FieldWithMetaString getCurrency() {
			return currency;
		}
		
		@Override
		@RosettaAttribute("velocity")
		public Velocity getVelocity() {
			return velocity;
		}
		
		@Override
		public LimitApplicable build() {
			return this;
		}
		
		@Override
		public LimitApplicable.LimitApplicableBuilder toBuilder() {
			LimitApplicable.LimitApplicableBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(LimitApplicable.LimitApplicableBuilder builder) {
			ofNullable(getLimitType()).ifPresent(builder::setLimitType);
			ofNullable(getClipSize()).ifPresent(builder::setClipSize);
			ofNullable(getAmountUtilized()).ifPresent(builder::setAmountUtilized);
			ofNullable(getUtilization()).ifPresent(builder::setUtilization);
			ofNullable(getAmountRemaining()).ifPresent(builder::setAmountRemaining);
			ofNullable(getCurrency()).ifPresent(builder::setCurrency);
			ofNullable(getVelocity()).ifPresent(builder::setVelocity);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			LimitApplicable _that = getType().cast(o);
		
			if (!Objects.equals(limitType, _that.getLimitType())) return false;
			if (!Objects.equals(clipSize, _that.getClipSize())) return false;
			if (!Objects.equals(amountUtilized, _that.getAmountUtilized())) return false;
			if (!Objects.equals(utilization, _that.getUtilization())) return false;
			if (!Objects.equals(amountRemaining, _that.getAmountRemaining())) return false;
			if (!Objects.equals(currency, _that.getCurrency())) return false;
			if (!Objects.equals(velocity, _that.getVelocity())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (limitType != null ? limitType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (clipSize != null ? clipSize.hashCode() : 0);
			_result = 31 * _result + (amountUtilized != null ? amountUtilized.hashCode() : 0);
			_result = 31 * _result + (utilization != null ? utilization.hashCode() : 0);
			_result = 31 * _result + (amountRemaining != null ? amountRemaining.hashCode() : 0);
			_result = 31 * _result + (currency != null ? currency.hashCode() : 0);
			_result = 31 * _result + (velocity != null ? velocity.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "LimitApplicable {" +
				"limitType=" + this.limitType + ", " +
				"clipSize=" + this.clipSize + ", " +
				"amountUtilized=" + this.amountUtilized + ", " +
				"utilization=" + this.utilization + ", " +
				"amountRemaining=" + this.amountRemaining + ", " +
				"currency=" + this.currency + ", " +
				"velocity=" + this.velocity +
			'}';
		}
	}

	/*********************** Builder Implementation of LimitApplicable  ***********************/
	class LimitApplicableBuilderImpl implements LimitApplicable.LimitApplicableBuilder {
	
		protected FieldWithMetaCreditLimitTypeEnum.FieldWithMetaCreditLimitTypeEnumBuilder limitType;
		protected Integer clipSize;
		protected BigDecimal amountUtilized;
		protected CreditLimitUtilisation.CreditLimitUtilisationBuilder utilization;
		protected BigDecimal amountRemaining;
		protected FieldWithMetaString.FieldWithMetaStringBuilder currency;
		protected Velocity.VelocityBuilder velocity;
	
		public LimitApplicableBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("limitType")
		public FieldWithMetaCreditLimitTypeEnum.FieldWithMetaCreditLimitTypeEnumBuilder getLimitType() {
			return limitType;
		}
		
		@Override
		public FieldWithMetaCreditLimitTypeEnum.FieldWithMetaCreditLimitTypeEnumBuilder getOrCreateLimitType() {
			FieldWithMetaCreditLimitTypeEnum.FieldWithMetaCreditLimitTypeEnumBuilder result;
			if (limitType!=null) {
				result = limitType;
			}
			else {
				result = limitType = FieldWithMetaCreditLimitTypeEnum.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("clipSize")
		public Integer getClipSize() {
			return clipSize;
		}
		
		@Override
		@RosettaAttribute("amountUtilized")
		public BigDecimal getAmountUtilized() {
			return amountUtilized;
		}
		
		@Override
		@RosettaAttribute("utilization")
		public CreditLimitUtilisation.CreditLimitUtilisationBuilder getUtilization() {
			return utilization;
		}
		
		@Override
		public CreditLimitUtilisation.CreditLimitUtilisationBuilder getOrCreateUtilization() {
			CreditLimitUtilisation.CreditLimitUtilisationBuilder result;
			if (utilization!=null) {
				result = utilization;
			}
			else {
				result = utilization = CreditLimitUtilisation.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("amountRemaining")
		public BigDecimal getAmountRemaining() {
			return amountRemaining;
		}
		
		@Override
		@RosettaAttribute("currency")
		public FieldWithMetaString.FieldWithMetaStringBuilder getCurrency() {
			return currency;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateCurrency() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (currency!=null) {
				result = currency;
			}
			else {
				result = currency = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("velocity")
		public Velocity.VelocityBuilder getVelocity() {
			return velocity;
		}
		
		@Override
		public Velocity.VelocityBuilder getOrCreateVelocity() {
			Velocity.VelocityBuilder result;
			if (velocity!=null) {
				result = velocity;
			}
			else {
				result = velocity = Velocity.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("limitType")
		public LimitApplicable.LimitApplicableBuilder setLimitType(FieldWithMetaCreditLimitTypeEnum limitType) {
			this.limitType = limitType==null?null:limitType.toBuilder();
			return this;
		}
		@Override
		public LimitApplicable.LimitApplicableBuilder setLimitTypeValue(CreditLimitTypeEnum limitType) {
			this.getOrCreateLimitType().setValue(limitType);
			return this;
		}
		@Override
		@RosettaAttribute("clipSize")
		public LimitApplicable.LimitApplicableBuilder setClipSize(Integer clipSize) {
			this.clipSize = clipSize==null?null:clipSize;
			return this;
		}
		@Override
		@RosettaAttribute("amountUtilized")
		public LimitApplicable.LimitApplicableBuilder setAmountUtilized(BigDecimal amountUtilized) {
			this.amountUtilized = amountUtilized==null?null:amountUtilized;
			return this;
		}
		@Override
		@RosettaAttribute("utilization")
		public LimitApplicable.LimitApplicableBuilder setUtilization(CreditLimitUtilisation utilization) {
			this.utilization = utilization==null?null:utilization.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("amountRemaining")
		public LimitApplicable.LimitApplicableBuilder setAmountRemaining(BigDecimal amountRemaining) {
			this.amountRemaining = amountRemaining==null?null:amountRemaining;
			return this;
		}
		@Override
		@RosettaAttribute("currency")
		public LimitApplicable.LimitApplicableBuilder setCurrency(FieldWithMetaString currency) {
			this.currency = currency==null?null:currency.toBuilder();
			return this;
		}
		@Override
		public LimitApplicable.LimitApplicableBuilder setCurrencyValue(String currency) {
			this.getOrCreateCurrency().setValue(currency);
			return this;
		}
		@Override
		@RosettaAttribute("velocity")
		public LimitApplicable.LimitApplicableBuilder setVelocity(Velocity velocity) {
			this.velocity = velocity==null?null:velocity.toBuilder();
			return this;
		}
		
		@Override
		public LimitApplicable build() {
			return new LimitApplicable.LimitApplicableImpl(this);
		}
		
		@Override
		public LimitApplicable.LimitApplicableBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public LimitApplicable.LimitApplicableBuilder prune() {
			if (limitType!=null && !limitType.prune().hasData()) limitType = null;
			if (utilization!=null && !utilization.prune().hasData()) utilization = null;
			if (currency!=null && !currency.prune().hasData()) currency = null;
			if (velocity!=null && !velocity.prune().hasData()) velocity = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getLimitType()!=null) return true;
			if (getClipSize()!=null) return true;
			if (getAmountUtilized()!=null) return true;
			if (getUtilization()!=null && getUtilization().hasData()) return true;
			if (getAmountRemaining()!=null) return true;
			if (getCurrency()!=null) return true;
			if (getVelocity()!=null && getVelocity().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public LimitApplicable.LimitApplicableBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			LimitApplicable.LimitApplicableBuilder o = (LimitApplicable.LimitApplicableBuilder) other;
			
			merger.mergeRosetta(getLimitType(), o.getLimitType(), this::setLimitType);
			merger.mergeRosetta(getUtilization(), o.getUtilization(), this::setUtilization);
			merger.mergeRosetta(getCurrency(), o.getCurrency(), this::setCurrency);
			merger.mergeRosetta(getVelocity(), o.getVelocity(), this::setVelocity);
			
			merger.mergeBasic(getClipSize(), o.getClipSize(), this::setClipSize);
			merger.mergeBasic(getAmountUtilized(), o.getAmountUtilized(), this::setAmountUtilized);
			merger.mergeBasic(getAmountRemaining(), o.getAmountRemaining(), this::setAmountRemaining);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			LimitApplicable _that = getType().cast(o);
		
			if (!Objects.equals(limitType, _that.getLimitType())) return false;
			if (!Objects.equals(clipSize, _that.getClipSize())) return false;
			if (!Objects.equals(amountUtilized, _that.getAmountUtilized())) return false;
			if (!Objects.equals(utilization, _that.getUtilization())) return false;
			if (!Objects.equals(amountRemaining, _that.getAmountRemaining())) return false;
			if (!Objects.equals(currency, _that.getCurrency())) return false;
			if (!Objects.equals(velocity, _that.getVelocity())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (limitType != null ? limitType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (clipSize != null ? clipSize.hashCode() : 0);
			_result = 31 * _result + (amountUtilized != null ? amountUtilized.hashCode() : 0);
			_result = 31 * _result + (utilization != null ? utilization.hashCode() : 0);
			_result = 31 * _result + (amountRemaining != null ? amountRemaining.hashCode() : 0);
			_result = 31 * _result + (currency != null ? currency.hashCode() : 0);
			_result = 31 * _result + (velocity != null ? velocity.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "LimitApplicableBuilder {" +
				"limitType=" + this.limitType + ", " +
				"clipSize=" + this.clipSize + ", " +
				"amountUtilized=" + this.amountUtilized + ", " +
				"utilization=" + this.utilization + ", " +
				"amountRemaining=" + this.amountRemaining + ", " +
				"currency=" + this.currency + ", " +
				"velocity=" + this.velocity +
			'}';
		}
	}
}
