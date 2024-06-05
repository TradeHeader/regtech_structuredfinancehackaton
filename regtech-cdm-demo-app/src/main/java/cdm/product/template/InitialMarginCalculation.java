package cdm.product.template;

import cdm.product.template.InitialMarginCalculation;
import cdm.product.template.InitialMarginCalculation.InitialMarginCalculationBuilder;
import cdm.product.template.InitialMarginCalculation.InitialMarginCalculationBuilderImpl;
import cdm.product.template.InitialMarginCalculation.InitialMarginCalculationImpl;
import cdm.product.template.meta.InitialMarginCalculationMeta;
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
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 *  Defines the initial margin calculation applicable to a single piece of collateral.
 * @version ${project.version}
 */
@RosettaDataType(value="InitialMarginCalculation", builder=InitialMarginCalculation.InitialMarginCalculationBuilderImpl.class, version="${project.version}")
public interface InitialMarginCalculation extends RosettaModelObject {

	InitialMarginCalculationMeta metaData = new InitialMarginCalculationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * An element defining an initial margin expressed as a ratio of the Market Value of the collateral to the Purchase Price. A default value of initial margin ratio of 1.00 means there is no margin and thus no risk related with the collateral. See GMRA 2000 paragraph 2(z) and GMRA 2011 paragraph 2(bb).
	 */
	BigDecimal getMarginRatio();
	/**
	 * An element defining a margin ratio threshold which is the value above (when it&#39;s lower than initial margin ratio) or below (when it&#39;s higher than initial margin ratio) which parties agree they will not call a margin from each other.
	 */
	List<BigDecimal> getMarginRatioThreshold();
	/**
	 * An element defining a haircut expressed as the percentage difference between the Market Value of the collateral and the Purchase Price of the repo and calculated as 100 multiplied by a ratio of the difference between the Market Value of the collateral and the Purchase Price of the repo to the Market Value of the collateral. Haircut is alternative way to adjust the value of collateral sold in a repurchase agreement to initial margin ratio. Because an initial margin is a percentage of the Purchase Price, while a haircut is a percentage of the Market Value of collateral, the arithmetic of initial margins and haircuts is slightly different. For example, an initial margin of 102% is not equivalent to a haircut of 2%, but to 1.961% (ie 100/102%). See GMRA 2011 paragraph 2(aa).
	 */
	BigDecimal getHaircut();
	/**
	 * An element defining a haircut percentage threshold which is the value above (when it&#39;s lower than initial haircut) or below (when it&#39;s higher than initial haircut) which parties agree they will not call a margin from each other.
	 */
	List<BigDecimal> getHaircutThreshold();

	/*********************** Build Methods  ***********************/
	InitialMarginCalculation build();
	
	InitialMarginCalculation.InitialMarginCalculationBuilder toBuilder();
	
	static InitialMarginCalculation.InitialMarginCalculationBuilder builder() {
		return new InitialMarginCalculation.InitialMarginCalculationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends InitialMarginCalculation> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends InitialMarginCalculation> getType() {
		return InitialMarginCalculation.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("marginRatio"), BigDecimal.class, getMarginRatio(), this);
		processor.processBasic(path.newSubPath("marginRatioThreshold"), BigDecimal.class, getMarginRatioThreshold(), this);
		processor.processBasic(path.newSubPath("haircut"), BigDecimal.class, getHaircut(), this);
		processor.processBasic(path.newSubPath("haircutThreshold"), BigDecimal.class, getHaircutThreshold(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface InitialMarginCalculationBuilder extends InitialMarginCalculation, RosettaModelObjectBuilder {
		InitialMarginCalculation.InitialMarginCalculationBuilder setMarginRatio(BigDecimal marginRatio);
		InitialMarginCalculation.InitialMarginCalculationBuilder addMarginRatioThreshold(BigDecimal marginRatioThreshold0);
		InitialMarginCalculation.InitialMarginCalculationBuilder addMarginRatioThreshold(BigDecimal marginRatioThreshold1, int _idx);
		InitialMarginCalculation.InitialMarginCalculationBuilder addMarginRatioThreshold(List<? extends BigDecimal> marginRatioThreshold2);
		InitialMarginCalculation.InitialMarginCalculationBuilder setMarginRatioThreshold(List<? extends BigDecimal> marginRatioThreshold3);
		InitialMarginCalculation.InitialMarginCalculationBuilder setHaircut(BigDecimal haircut);
		InitialMarginCalculation.InitialMarginCalculationBuilder addHaircutThreshold(BigDecimal haircutThreshold0);
		InitialMarginCalculation.InitialMarginCalculationBuilder addHaircutThreshold(BigDecimal haircutThreshold1, int _idx);
		InitialMarginCalculation.InitialMarginCalculationBuilder addHaircutThreshold(List<? extends BigDecimal> haircutThreshold2);
		InitialMarginCalculation.InitialMarginCalculationBuilder setHaircutThreshold(List<? extends BigDecimal> haircutThreshold3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("marginRatio"), BigDecimal.class, getMarginRatio(), this);
			processor.processBasic(path.newSubPath("marginRatioThreshold"), BigDecimal.class, getMarginRatioThreshold(), this);
			processor.processBasic(path.newSubPath("haircut"), BigDecimal.class, getHaircut(), this);
			processor.processBasic(path.newSubPath("haircutThreshold"), BigDecimal.class, getHaircutThreshold(), this);
		}
		

		InitialMarginCalculation.InitialMarginCalculationBuilder prune();
	}

	/*********************** Immutable Implementation of InitialMarginCalculation  ***********************/
	class InitialMarginCalculationImpl implements InitialMarginCalculation {
		private final BigDecimal marginRatio;
		private final List<BigDecimal> marginRatioThreshold;
		private final BigDecimal haircut;
		private final List<BigDecimal> haircutThreshold;
		
		protected InitialMarginCalculationImpl(InitialMarginCalculation.InitialMarginCalculationBuilder builder) {
			this.marginRatio = builder.getMarginRatio();
			this.marginRatioThreshold = ofNullable(builder.getMarginRatioThreshold()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.haircut = builder.getHaircut();
			this.haircutThreshold = ofNullable(builder.getHaircutThreshold()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
		}
		
		@Override
		@RosettaAttribute("marginRatio")
		public BigDecimal getMarginRatio() {
			return marginRatio;
		}
		
		@Override
		@RosettaAttribute("marginRatioThreshold")
		public List<BigDecimal> getMarginRatioThreshold() {
			return marginRatioThreshold;
		}
		
		@Override
		@RosettaAttribute("haircut")
		public BigDecimal getHaircut() {
			return haircut;
		}
		
		@Override
		@RosettaAttribute("haircutThreshold")
		public List<BigDecimal> getHaircutThreshold() {
			return haircutThreshold;
		}
		
		@Override
		public InitialMarginCalculation build() {
			return this;
		}
		
		@Override
		public InitialMarginCalculation.InitialMarginCalculationBuilder toBuilder() {
			InitialMarginCalculation.InitialMarginCalculationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(InitialMarginCalculation.InitialMarginCalculationBuilder builder) {
			ofNullable(getMarginRatio()).ifPresent(builder::setMarginRatio);
			ofNullable(getMarginRatioThreshold()).ifPresent(builder::setMarginRatioThreshold);
			ofNullable(getHaircut()).ifPresent(builder::setHaircut);
			ofNullable(getHaircutThreshold()).ifPresent(builder::setHaircutThreshold);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			InitialMarginCalculation _that = getType().cast(o);
		
			if (!Objects.equals(marginRatio, _that.getMarginRatio())) return false;
			if (!ListEquals.listEquals(marginRatioThreshold, _that.getMarginRatioThreshold())) return false;
			if (!Objects.equals(haircut, _that.getHaircut())) return false;
			if (!ListEquals.listEquals(haircutThreshold, _that.getHaircutThreshold())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (marginRatio != null ? marginRatio.hashCode() : 0);
			_result = 31 * _result + (marginRatioThreshold != null ? marginRatioThreshold.hashCode() : 0);
			_result = 31 * _result + (haircut != null ? haircut.hashCode() : 0);
			_result = 31 * _result + (haircutThreshold != null ? haircutThreshold.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "InitialMarginCalculation {" +
				"marginRatio=" + this.marginRatio + ", " +
				"marginRatioThreshold=" + this.marginRatioThreshold + ", " +
				"haircut=" + this.haircut + ", " +
				"haircutThreshold=" + this.haircutThreshold +
			'}';
		}
	}

	/*********************** Builder Implementation of InitialMarginCalculation  ***********************/
	class InitialMarginCalculationBuilderImpl implements InitialMarginCalculation.InitialMarginCalculationBuilder {
	
		protected BigDecimal marginRatio;
		protected List<BigDecimal> marginRatioThreshold = new ArrayList<>();
		protected BigDecimal haircut;
		protected List<BigDecimal> haircutThreshold = new ArrayList<>();
	
		public InitialMarginCalculationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("marginRatio")
		public BigDecimal getMarginRatio() {
			return marginRatio;
		}
		
		@Override
		@RosettaAttribute("marginRatioThreshold")
		public List<BigDecimal> getMarginRatioThreshold() {
			return marginRatioThreshold;
		}
		
		@Override
		@RosettaAttribute("haircut")
		public BigDecimal getHaircut() {
			return haircut;
		}
		
		@Override
		@RosettaAttribute("haircutThreshold")
		public List<BigDecimal> getHaircutThreshold() {
			return haircutThreshold;
		}
		
	
		@Override
		@RosettaAttribute("marginRatio")
		public InitialMarginCalculation.InitialMarginCalculationBuilder setMarginRatio(BigDecimal marginRatio) {
			this.marginRatio = marginRatio==null?null:marginRatio;
			return this;
		}
		@Override
		public InitialMarginCalculation.InitialMarginCalculationBuilder addMarginRatioThreshold(BigDecimal marginRatioThreshold) {
			if (marginRatioThreshold!=null) this.marginRatioThreshold.add(marginRatioThreshold);
			return this;
		}
		
		@Override
		public InitialMarginCalculation.InitialMarginCalculationBuilder addMarginRatioThreshold(BigDecimal marginRatioThreshold, int _idx) {
			getIndex(this.marginRatioThreshold, _idx, () -> marginRatioThreshold);
			return this;
		}
		@Override 
		public InitialMarginCalculation.InitialMarginCalculationBuilder addMarginRatioThreshold(List<? extends BigDecimal> marginRatioThresholds) {
			if (marginRatioThresholds != null) {
				for (BigDecimal toAdd : marginRatioThresholds) {
					this.marginRatioThreshold.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("marginRatioThreshold")
		public InitialMarginCalculation.InitialMarginCalculationBuilder setMarginRatioThreshold(List<? extends BigDecimal> marginRatioThresholds) {
			if (marginRatioThresholds == null)  {
				this.marginRatioThreshold = new ArrayList<>();
			}
			else {
				this.marginRatioThreshold = marginRatioThresholds.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("haircut")
		public InitialMarginCalculation.InitialMarginCalculationBuilder setHaircut(BigDecimal haircut) {
			this.haircut = haircut==null?null:haircut;
			return this;
		}
		@Override
		public InitialMarginCalculation.InitialMarginCalculationBuilder addHaircutThreshold(BigDecimal haircutThreshold) {
			if (haircutThreshold!=null) this.haircutThreshold.add(haircutThreshold);
			return this;
		}
		
		@Override
		public InitialMarginCalculation.InitialMarginCalculationBuilder addHaircutThreshold(BigDecimal haircutThreshold, int _idx) {
			getIndex(this.haircutThreshold, _idx, () -> haircutThreshold);
			return this;
		}
		@Override 
		public InitialMarginCalculation.InitialMarginCalculationBuilder addHaircutThreshold(List<? extends BigDecimal> haircutThresholds) {
			if (haircutThresholds != null) {
				for (BigDecimal toAdd : haircutThresholds) {
					this.haircutThreshold.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("haircutThreshold")
		public InitialMarginCalculation.InitialMarginCalculationBuilder setHaircutThreshold(List<? extends BigDecimal> haircutThresholds) {
			if (haircutThresholds == null)  {
				this.haircutThreshold = new ArrayList<>();
			}
			else {
				this.haircutThreshold = haircutThresholds.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public InitialMarginCalculation build() {
			return new InitialMarginCalculation.InitialMarginCalculationImpl(this);
		}
		
		@Override
		public InitialMarginCalculation.InitialMarginCalculationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public InitialMarginCalculation.InitialMarginCalculationBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getMarginRatio()!=null) return true;
			if (getMarginRatioThreshold()!=null && !getMarginRatioThreshold().isEmpty()) return true;
			if (getHaircut()!=null) return true;
			if (getHaircutThreshold()!=null && !getHaircutThreshold().isEmpty()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public InitialMarginCalculation.InitialMarginCalculationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			InitialMarginCalculation.InitialMarginCalculationBuilder o = (InitialMarginCalculation.InitialMarginCalculationBuilder) other;
			
			
			merger.mergeBasic(getMarginRatio(), o.getMarginRatio(), this::setMarginRatio);
			merger.mergeBasic(getMarginRatioThreshold(), o.getMarginRatioThreshold(), (Consumer<BigDecimal>) this::addMarginRatioThreshold);
			merger.mergeBasic(getHaircut(), o.getHaircut(), this::setHaircut);
			merger.mergeBasic(getHaircutThreshold(), o.getHaircutThreshold(), (Consumer<BigDecimal>) this::addHaircutThreshold);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			InitialMarginCalculation _that = getType().cast(o);
		
			if (!Objects.equals(marginRatio, _that.getMarginRatio())) return false;
			if (!ListEquals.listEquals(marginRatioThreshold, _that.getMarginRatioThreshold())) return false;
			if (!Objects.equals(haircut, _that.getHaircut())) return false;
			if (!ListEquals.listEquals(haircutThreshold, _that.getHaircutThreshold())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (marginRatio != null ? marginRatio.hashCode() : 0);
			_result = 31 * _result + (marginRatioThreshold != null ? marginRatioThreshold.hashCode() : 0);
			_result = 31 * _result + (haircut != null ? haircut.hashCode() : 0);
			_result = 31 * _result + (haircutThreshold != null ? haircutThreshold.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "InitialMarginCalculationBuilder {" +
				"marginRatio=" + this.marginRatio + ", " +
				"marginRatioThreshold=" + this.marginRatioThreshold + ", " +
				"haircut=" + this.haircut + ", " +
				"haircutThreshold=" + this.haircutThreshold +
			'}';
		}
	}
}
