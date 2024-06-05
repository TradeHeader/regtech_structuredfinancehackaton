package cdm.regulation;

import cdm.regulation.Swp;
import cdm.regulation.UndrlygInstrm;
import cdm.regulation.UndrlygInstrm.UndrlygInstrmBuilder;
import cdm.regulation.UndrlygInstrm.UndrlygInstrmBuilderImpl;
import cdm.regulation.UndrlygInstrm.UndrlygInstrmImpl;
import cdm.regulation.meta.UndrlygInstrmMeta;
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
 * @version ${project.version}
 */
@RosettaDataType(value="UndrlygInstrm", builder=UndrlygInstrm.UndrlygInstrmBuilderImpl.class, version="${project.version}")
public interface UndrlygInstrm extends RosettaModelObject {

	UndrlygInstrmMeta metaData = new UndrlygInstrmMeta();

	/*********************** Getter Methods  ***********************/
	Swp getSwp();

	/*********************** Build Methods  ***********************/
	UndrlygInstrm build();
	
	UndrlygInstrm.UndrlygInstrmBuilder toBuilder();
	
	static UndrlygInstrm.UndrlygInstrmBuilder builder() {
		return new UndrlygInstrm.UndrlygInstrmBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends UndrlygInstrm> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends UndrlygInstrm> getType() {
		return UndrlygInstrm.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("swp"), processor, Swp.class, getSwp());
	}
	

	/*********************** Builder Interface  ***********************/
	interface UndrlygInstrmBuilder extends UndrlygInstrm, RosettaModelObjectBuilder {
		Swp.SwpBuilder getOrCreateSwp();
		Swp.SwpBuilder getSwp();
		UndrlygInstrm.UndrlygInstrmBuilder setSwp(Swp swp);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("swp"), processor, Swp.SwpBuilder.class, getSwp());
		}
		

		UndrlygInstrm.UndrlygInstrmBuilder prune();
	}

	/*********************** Immutable Implementation of UndrlygInstrm  ***********************/
	class UndrlygInstrmImpl implements UndrlygInstrm {
		private final Swp swp;
		
		protected UndrlygInstrmImpl(UndrlygInstrm.UndrlygInstrmBuilder builder) {
			this.swp = ofNullable(builder.getSwp()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("swp")
		public Swp getSwp() {
			return swp;
		}
		
		@Override
		public UndrlygInstrm build() {
			return this;
		}
		
		@Override
		public UndrlygInstrm.UndrlygInstrmBuilder toBuilder() {
			UndrlygInstrm.UndrlygInstrmBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(UndrlygInstrm.UndrlygInstrmBuilder builder) {
			ofNullable(getSwp()).ifPresent(builder::setSwp);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			UndrlygInstrm _that = getType().cast(o);
		
			if (!Objects.equals(swp, _that.getSwp())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (swp != null ? swp.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "UndrlygInstrm {" +
				"swp=" + this.swp +
			'}';
		}
	}

	/*********************** Builder Implementation of UndrlygInstrm  ***********************/
	class UndrlygInstrmBuilderImpl implements UndrlygInstrm.UndrlygInstrmBuilder {
	
		protected Swp.SwpBuilder swp;
	
		public UndrlygInstrmBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("swp")
		public Swp.SwpBuilder getSwp() {
			return swp;
		}
		
		@Override
		public Swp.SwpBuilder getOrCreateSwp() {
			Swp.SwpBuilder result;
			if (swp!=null) {
				result = swp;
			}
			else {
				result = swp = Swp.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("swp")
		public UndrlygInstrm.UndrlygInstrmBuilder setSwp(Swp swp) {
			this.swp = swp==null?null:swp.toBuilder();
			return this;
		}
		
		@Override
		public UndrlygInstrm build() {
			return new UndrlygInstrm.UndrlygInstrmImpl(this);
		}
		
		@Override
		public UndrlygInstrm.UndrlygInstrmBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public UndrlygInstrm.UndrlygInstrmBuilder prune() {
			if (swp!=null && !swp.prune().hasData()) swp = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getSwp()!=null && getSwp().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public UndrlygInstrm.UndrlygInstrmBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			UndrlygInstrm.UndrlygInstrmBuilder o = (UndrlygInstrm.UndrlygInstrmBuilder) other;
			
			merger.mergeRosetta(getSwp(), o.getSwp(), this::setSwp);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			UndrlygInstrm _that = getType().cast(o);
		
			if (!Objects.equals(swp, _that.getSwp())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (swp != null ? swp.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "UndrlygInstrmBuilder {" +
				"swp=" + this.swp +
			'}';
		}
	}
}
